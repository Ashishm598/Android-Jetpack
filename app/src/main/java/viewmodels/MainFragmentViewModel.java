package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import pojo.response.Response;
import repositories.DataRepository;
import ui.fragment.main.MainFragmentContract;

public class MainFragmentViewModel extends ViewModel implements MainFragmentContract.ViewModel {

    private MutableLiveData<List<Response>> responseLiveData;
    private MutableLiveData<Boolean> isOnProgress = new MutableLiveData<>();
    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void init() {
        dataRepository = new DataRepository();
    }

    @Override
    public LiveData<List<Response>> getResponse() {

        isOnProgress.postValue(true);

        dataRepository.getResponseObservable().subscribe(new Observer<List<Response>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Response> responses) {
                if (responses != null && responses.size() > 0) {
                    responseLiveData = new MutableLiveData<>();
                    responseLiveData.setValue(responses);
                }
            }

            @Override
            public void onError(Throwable e) {
                isOnProgress.postValue(false);
            }

            @Override
            public void onComplete() {
                isOnProgress.postValue(false);
            }
        });

        return responseLiveData;
    }


    @Override
    public LiveData<List<Response>> getResponseLiveData() {
        return (responseLiveData == null) ? getResponse() : responseLiveData;
    }

    @Override
    public LiveData<Boolean> getProgressUpdate() {
        return isOnProgress;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
