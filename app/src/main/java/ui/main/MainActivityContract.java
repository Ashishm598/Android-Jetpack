package ui.main;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Observable;
import pojo.response.Response;

public interface MainActivityContract {

    interface View {
        void initRecyclerView();
        void showProgressBar();
        void hideProgressBar();
        void loadData();
    }

    interface ViewModel {
       void init();
       void getResponse();
       LiveData<List<Response>> getResponseLiveData();
       LiveData<Boolean> getProgressUpdate();

    }

    interface Repo {
        Observable<List<Response>> getResponseObservable();
    }

}
