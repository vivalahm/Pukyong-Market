package pknu.it.pknuapp.Activities.ui.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class infoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public infoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}