package pknu.it.pknuapp.Activities.ui.creator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;



import pknu.it.pknuapp.Activities.ui.info.infoViewModel;
import pknu.it.pknuapp.R;


public class creatorFragment extends Fragment {

    private pknu.it.pknuapp.Activities.ui.info.infoViewModel infoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        infoViewModel =
                ViewModelProviders.of(this).get(infoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_creator, container, false);

        return root;
    }
}