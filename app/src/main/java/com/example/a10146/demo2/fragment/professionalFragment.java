package com.example.a10146.demo2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.adapter.ProfessionalAdpter;
import com.example.a10146.demo2.adapter.demotestadapter;
import com.example.a10146.demo2.professionalTest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class professionalFragment extends Fragment {
    ProfessionalAdpter madapter;
//    @BindView(R.id.professionalbtn)
//    Button professionalBtn;
    @BindView(R.id.Tips)
    LinearLayout  tipscontent;
    RecyclerView recyclerView;

    private List<professionalTest> newsList;

    private demotestadapter readapter;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

          View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_profession, null);
            ButterKnife.bind(getActivity(), view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        recyclerView= (RecyclerView) view.findViewById(R.id.profssion_recyclerView);

        initPersonData();


         recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        readapter=new demotestadapter(newsList,getActivity());

        recyclerView.setAdapter(readapter);


        return view;
    }


    @Override
    public void onStart() {

        super.onStart();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

//    @OnClick(R.id.professionalbtn)
//    void onClick(View view) {
//        tipscontent.setVisibility(View.GONE);
//
//
//    }

    private void initPersonData() {
        newsList =new ArrayList<>();

        newsList.add(new professionalTest(getString(R.string.news_one_title),
                getString(R.string.news_one_desc),R.mipmap.news_one));

        newsList.add(new professionalTest(getString(R.string.news_two_title),
                getString(R.string.news_two_desc),R.mipmap.news_two));

        newsList.add(new professionalTest(getString(R.string.news_three_title),
                getString(R.string.news_three_desc),R.mipmap.news_three));

        newsList.add(new professionalTest(getString(R.string.news_four_title),getString(R.string.news_four_desc),R.mipmap.news_four));
    }

}