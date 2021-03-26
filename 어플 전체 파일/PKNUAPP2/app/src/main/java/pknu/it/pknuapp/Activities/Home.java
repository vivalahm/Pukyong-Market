package pknu.it.pknuapp.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.Menu;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import pknu.it.pknuapp.Models.Post;
import pknu.it.pknuapp.R;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2;
    FirebaseAuth mAuth ;
    FirebaseUser currentUser ;
    Dialog popAddPost ;
    ImageView popupUserImage,popupPostImage,popupAddBtn;
    TextView popupTitle,popupPrice,popupMajor, popupDescription;
    ProgressBar popupClickprogress;
    private Uri pickedImgUri= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth =FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //팝업 초기화
        iniPopup();
        setupPopupImageClick();

        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popAddPost.show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_info,
                R.id.nav_creator,
                R.id.nav_signout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull
                    NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nav_home) {
                    getSupportActionBar().setTitle("책 거래");
                    Toast.makeText(Home.this, "책 거래 페이지입니다.", Toast.LENGTH_SHORT).show();
                }
                if (destination.getId() == R.id.nav_info) {
                    getSupportActionBar().setTitle("사용법");
                    Toast.makeText(Home.this, "사용법 페이지입니다.", Toast.LENGTH_SHORT).show();
                }
                if (destination.getId() == R.id.nav_creator) {
                    getSupportActionBar().setTitle("개발자");
                    Toast.makeText(Home.this, "개발자 페이지입니다.", Toast.LENGTH_SHORT).show();
                }
                if (destination.getId() == R.id.nav_signout) {
                    getSupportActionBar().setTitle("로그아웃중..");
                    Toast.makeText(Home.this, "로그아웃됨", Toast.LENGTH_LONG).show();
                    FirebaseAuth.getInstance().signOut();
                    Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginActivity);
                    finish();


                }
            }
        });

        updateNavHeader();

    }



    private void setupPopupImageClick() {

        popupPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지를 누르면 갤러리로 염
                //갤러리 열려면 퍼미션 허락 필요
                checkAndRequestForPermission();

            }
        });
    }


    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(Home.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(Home.this,"필수 권한을 승인해주시기 바랍니다.",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(Home.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }


    private void openGallery() {


        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    //사용자가 사진을 선택할때
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {


            pickedImgUri = data.getData() ;
            popupPostImage.setImageURI(pickedImgUri);


        }


    }


    private void iniPopup() {
        popAddPost = new Dialog(this);
        popAddPost.setContentView(R.layout.popup_add_post);
        popAddPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddPost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.getWindow().getAttributes().gravity = Gravity.TOP;

        //팝업 위젯들 초기화
        popupUserImage = popAddPost.findViewById(R.id.popup_user_image);
        popupPostImage = popAddPost.findViewById(R.id.popup_img);
        popupTitle = popAddPost.findViewById(R.id.popup_title);
        popupPrice= popAddPost.findViewById(R.id.popup_price);
        popupMajor= popAddPost.findViewById(R.id.popup_major);
        popupDescription = popAddPost.findViewById(R.id.popup_description);
        popupAddBtn = popAddPost.findViewById(R.id.popup_add);
        popupClickprogress = popAddPost.findViewById(R.id.popup_progressBar);

        //사용자 현재 프로필 사진 불러오기
        Glide.with(Home.this).load(currentUser.getPhotoUrl()).into(popupUserImage);

        //새로운 게시글 추가 클릭 리스너
        popupAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupAddBtn.setVisibility(View.INVISIBLE);
                popupClickprogress.setVisibility(View.VISIBLE);

                if(!popupTitle.getText().toString().isEmpty()
                && !popupDescription.getText().toString().isEmpty()
                        &&!popupPrice.getText().toString().isEmpty()
                        &&!popupMajor.getText().toString().isEmpty()
                && pickedImgUri != null){

                    //새로운 게시글 항목을 올리고 파이어베이스 데이터베이스에 추가
                    //먼저 이미지를 업로드
                    // 파이어 베이스 저장소 접근
                    StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("Book_image");
                    final StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownloadLink = uri.toString();
                                    //게시물 항목 생성

                                    if(currentUser.getPhotoUrl() != null){
                                        Post post = new Post(popupTitle.getText().toString(),
                                                popupPrice.getText().toString(),
                                                popupMajor.getText().toString(),
                                                popupDescription.getText().toString(),
                                                imageDownloadLink,
                                                currentUser.getUid(),
                                                currentUser.getPhotoUrl().toString());

                                        //게시글 파이어베이스 데이터베이스에 추가

                                        addPost(post);
                                    }
                                    else{
                                        Post post = new Post(popupTitle.getText().toString(),
                                                popupPrice.getText().toString(),
                                                popupMajor.getText().toString(),
                                                popupDescription.getText().toString(),
                                                imageDownloadLink,
                                                currentUser.getUid(),
                                                null);

                                        //게시글 파이어베이스 데이터베이스에 추가

                                        addPost(post);
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //업로드 오류 발생시
                                    showMessage(e.getMessage());
                                    popupClickprogress.setVisibility(View.INVISIBLE);
                                    popupAddBtn.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    });
                    
                }
                else{
                    showMessage("모든 요소가 입력되었고, 이미지가 입력되었는지 확인해주세요.");
                    popupAddBtn.setVisibility(View.VISIBLE);
                    popupClickprogress.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    private void addPost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Books").push();

        //게시물 유일한 아이디와 업데이트 게시글 키 부여
        String key =myRef.getKey();
        post.setPostKey(key);

        //게시물 데이터 파이어베이스 데이터베이스에 추가
        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showMessage("게시물 추가 성공!");
                popupClickprogress.setVisibility(View.INVISIBLE);
                popupAddBtn.setVisibility(View.VISIBLE);
                popAddPost.dismiss();
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(Home.this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    public void updateNavHeader(){

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername =headerView.findViewById(R.id.nav_username);
        TextView navUsermail=headerView.findViewById(R.id.nav_user_mail);
        ImageView navUserPhoto =headerView.findViewById(R.id.nav_user_photo);

        navUsermail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());

        if(currentUser.getPhotoUrl() != null){
            Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);

        }else {

            Glide.with(this).load(R.drawable.userphoto).into(navUserPhoto);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
