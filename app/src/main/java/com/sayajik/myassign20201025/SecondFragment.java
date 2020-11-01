package com.sayajik.myassign20201025;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sayajik.myassign20201025.model.ShapeDataModel;

public class SecondFragment extends Fragment implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    ImageView ivShape = null;
    EditText edtComment = null;
    Button btnSubmit = null;

    ShapeDataModel shapeDataModel = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        shapeDataModel = (ShapeDataModel) bundle.getSerializable("selectedData");
        Log.d(TAG, shapeDataModel.getTitle());
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Image View Screen");
//        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                NavHostFragment.findNavController(SecondFragment.this)
////                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//                getActivity().setTitle("Search Screen");
//                getActivity().onBackPressed();
//            }
//        });
        ivShape = (ImageView) view.findViewById(R.id.iv_shape);
        edtComment = (EditText) view.findViewById(R.id.edt_comment);
        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

        edtComment.setText(shapeDataModel.getLink());
        Glide.with(getActivity())
                .load(shapeDataModel.getLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading)
                .into(ivShape);


    }

    @Override
    public void onClick(View view) {

    }
}