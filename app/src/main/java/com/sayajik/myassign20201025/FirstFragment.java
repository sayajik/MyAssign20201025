package com.sayajik.myassign20201025;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.sayajik.myassign20201025.adapters.ShapeAdapter;
import com.sayajik.myassign20201025.adapters.SimpleDividerItemDecoration;
import com.sayajik.myassign20201025.model.SearchResponse;
import com.sayajik.myassign20201025.model.ShapeDataModel;
import com.sayajik.myassign20201025.networking.RetrofitClientInstance;
import com.sayajik.myassign20201025.networking.ShapeApi;
import com.sayajik.myassign20201025.viewmodels.ShapeViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment implements View.OnClickListener{

    private final String TAG = getClass().getSimpleName();

    List<ShapeDataModel> shapeArrayList = new ArrayList<>();
    ShapeAdapter shapeAdapter;
    RecyclerView rvShape;
    EditText edtSearch;
    ImageButton imgSearch;
    ShapeViewModel shapeViewModel;

    ProgressDialog progress;
    ShapeAdapter.OnItemClickListener onItemClickListener = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Search Screen");
        rvShape = view.findViewById(R.id.rv_shape);
        edtSearch = view.findViewById(R.id.edt_search);
        imgSearch = view.findViewById(R.id.btn_search);
        imgSearch.setOnClickListener(this);
        progress = new ProgressDialog(getActivity());
        onItemClickListener = new ShapeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "Position - "+shapeArrayList.get(position).getTitle());
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.SecondFragment);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedData", shapeArrayList.get(position));

                SecondFragment fragment=new SecondFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.add(R.id.nav_host_fragment, fragment);
                ft.hide(FirstFragment.this);
                ft.addToBackStack(FirstFragment.class.getName());
                ft.commit();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        };
    }

    private void generateDataList(SearchResponse photoList) {
        if (photoList != null) {
            shapeArrayList = photoList.getSearchList();
            Log.d(TAG, photoList.getSearchList().size() + "");
            shapeAdapter = new ShapeAdapter(getActivity(), photoList.getSearchList(), onItemClickListener);
//            rvShape.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
            AutoFitGridLayoutManager layoutManager1 = new AutoFitGridLayoutManager(getActivity(), 500, RecyclerView.VERTICAL);
            layoutManager1.setOrientation(RecyclerView.VERTICAL);
            rvShape.setLayoutManager(layoutManager1);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        rvShape.setLayoutManager(layoutManager);
            rvShape.setAdapter(shapeAdapter);
        } else {
            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        searchData();
    }

    void searchData() {
        progress.setMessage("Loading....");
        progress.show();
//        shapeViewModel = ViewModelProviders.of(getActivity()).get(ShapeViewModel.class);
//        shapeViewModel.init();
//        shapeViewModel.getShapeRepository().observe(getActivity(), searchResponse -> {
//            List<ShapeDataModel> shapeDataModels = searchResponse.getSearchList();
//            shapeArrayList.addAll(shapeDataModels);
//            shapeAdapter.notifyDataSetChanged();
//        });
//        setupRecyclerView();
        ShapeApi service = RetrofitClientInstance.getRetrofitInstance().create(ShapeApi.class);
        Call<SearchResponse> call = service.getShapeData("Client-ID 137cda6b5008a7c", edtSearch.getText().toString(), "png");
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                progress.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                progress.dismiss();
                System.out.println(t.fillInStackTrace());
                Log.d(TAG, t.fillInStackTrace().toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("FirstFragment");
    }
}