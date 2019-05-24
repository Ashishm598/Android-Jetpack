package repositories;

import java.util.List;

import api.RetrofitClient;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pojo.response.Response;
import ui.fragment.main.MainFragmentContract;

public class DataRepository implements MainFragmentContract.Repo {

    @Override
    public Observable<List<Response>> getResponseObservable() {
        return RetrofitClient.getAPI().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
