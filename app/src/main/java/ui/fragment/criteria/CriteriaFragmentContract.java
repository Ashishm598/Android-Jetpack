package ui.fragment.criteria;

import java.util.List;

import pojo.response.Criteria;

public interface CriteriaFragmentContract {

    interface View {
        void initView();
        void initRecyclerView();
        void setSpans(List<Criteria> criteria);
    }

    interface ViewModel {


    }

    interface Repo {

    }

}
