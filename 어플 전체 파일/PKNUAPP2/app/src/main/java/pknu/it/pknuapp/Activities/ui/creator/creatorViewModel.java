package pknu.it.pknuapp.Activities.ui.creator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class creatorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public creatorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("이 페이지는 개발자 페이지 입니다.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}