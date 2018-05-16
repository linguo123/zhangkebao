package com.example.a10146.demo2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.adapter.ProfessionalAdpter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class professionalFragment extends Fragment {
    ProfessionalAdpter madapter;
    @BindView(R.id.professionaltest)
    RecyclerView  profTest;
    @BindView(R.id.professionalbtn)
    Button professionalBtn;
    @BindView(R.id.Tips)
    LinearLayout  tipscontent;
    @BindView(R.id.profTestSample)
    ViewStub testSample;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
//        View view = inflater.inflate(R.layout.fragment_profession, container,false);
          View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_profession, null);
     ButterKnife.bind(getActivity(), view);
//        profTest.setLayoutManager(new LinearLayoutManager(getActivity()));;
        return view;
    }
/*
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profTest.setLayoutManager(new LinearLayoutManager(getActivity()));;
        profTest.setAdapter(new ProfessionalAdpter(getActivity()));

    }*/

    @Override
    public void onStart() {

        super.onStart();
        profTest.setLayoutManager(new LinearLayoutManager(getActivity()));;
        profTest.setAdapter(new ProfessionalAdpter(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick(R.id.professionalbtn)
    void onClick(View view) {
        tipscontent.setVisibility(View.GONE);
        testSample.setVisibility(View.VISIBLE);

    }

}