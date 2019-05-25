package ui.fragment.main;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Observable;
import pojo.response.Response;

public interface MainFragmentContract {

    interface View {
        void initRecyclerView();

        void showProgressBar();

        void hideProgressBar();

        void loadData();
    }

    interface ViewModel {
        void init();

        LiveData<List<Response>> getResponse();

        LiveData<List<Response>> getResponseLiveData();

        LiveData<Boolean> getProgressUpdate();

    }

    interface Repo {
        Observable<List<Response>> getResponseObservable();
    }

}
