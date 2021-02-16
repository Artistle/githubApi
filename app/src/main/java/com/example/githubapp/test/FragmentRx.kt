package com.example.githubapp.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentDbBinding

import io.reactivex.disposables.CompositeDisposable

// TODO: 16.02.2021
class FragmentRx: Fragment() {
    private val mDisposable = CompositeDisposable()

    private lateinit var mBinding:FragmentDbBinding
    private lateinit var mViewModel:GetRepos
    private lateinit var mAdapter:RepoRxAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentDbBinding.inflate(inflater,container,false)
        mAdapter = RepoRxAdapter()
        mBinding.recyclerSave.adapter = mAdapter
        mViewModel = ViewModelProvider(this, )
        mDisposable.add(mViewModel.getRepos().subscribe{
            mAdapter.submitData(lifecycle,it)
        })

        return view
    }


}