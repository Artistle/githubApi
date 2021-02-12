package com.example.githubapp.fragments

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.githubapp.DI.AppConstants
import toothpick.InjectConstructor
import toothpick.Scope
import toothpick.ktp.KTP
import toothpick.smoothie.lifecycle.closeOnDestroy
class BaseFragment(@LayoutRes contextLayoutId:Int):Fragment(contextLayoutId) {}