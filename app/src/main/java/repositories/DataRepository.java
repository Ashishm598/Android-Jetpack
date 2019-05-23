package repositories;

import java.util.List;

import api.RetrofitClient;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pojo.response.Response;
import ui.main.MainActivityContract;

public class DataRepository implements MainActivityContract.Repo {

    @Override
    public Observable<List<Response>> getResponseObservable() {
        return RetrofitClient.getAPI().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
