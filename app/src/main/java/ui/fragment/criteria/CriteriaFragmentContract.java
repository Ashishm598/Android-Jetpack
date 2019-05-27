package ui.fragment.criteria;

import java.util.List;

import pojo.response.Criteria;

public interface CriteriaFragmentContract {

    interface View {
        void initView();
        void initRecyclerView(List<Criteria> criteriaList);
    }

    interface ViewModel {


    }

    interface Repo {

    }

}
