package com.example.RebmemMusicPlayer.views.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.RebmemMusicPlayer.R
import com.example.RebmemMusicPlayer.adapter.ViewPagerFragmentAdapter
import com.example.RebmemMusicPlayer.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTabs()

//        binding.toolbar.appName.layoutParams.height = (ScreenSizeUtils.getScreenHeight()*0.5/10).toInt()
//        binding.toolbar.appName.requestLayout()
    }

    private fun initTabs() {

        val tabNames = resources.getStringArray(R.array.tabNames)

        val adapter = ViewPagerFragmentAdapter(requireActivity().supportFragmentManager, lifecycle)
        adapter.addFragment(LibraryFragment())
//        adapter.addFragment(RecentlyAdded())
        adapter.addFragment(PlaylistFragment())
        adapter.addFragment(FavoriteFragment())
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpager.adapter = adapter


        binding.toolbar.tabLayoutHome.visibility = View.GONE

        TabLayoutMediator(binding.toolbar.tabLayoutHome, binding.viewpager)
        { tab, position ->
            tab.text = tabNames[position]
        }.attach()


        binding.toolbar.expandableToolbar.expandableToolbarComponent.onItemSelectedListener =
            { view, menuItem, b ->
                when (menuItem.text) {
                    getString(R.string.songs_tab) -> {

                        binding.viewpager.setCurrentItem(0, true)
                    }

                    getString(R.string.playlists_tab) -> {

                        binding.viewpager.setCurrentItem(1, true)
                    }
                    getString(R.string.favorites_tab) -> {

                        binding.viewpager.setCurrentItem(2, true)
                    }
                }
            }

        binding.toolbar.tabLayoutHome.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

                when (tab?.text) {
                    "Library" -> binding.toolbar.expandableToolbar.expandableToolbarComponent.menu.select(
                        R.id.songs
                    )
                    "Playlists" -> binding.toolbar.expandableToolbar.expandableToolbarComponent.menu.select(
                        R.id.playlist
                    )
                    "Favorite" -> binding.toolbar.expandableToolbar.expandableToolbarComponent.menu.select(
                        R.id.favorites
                    )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "Library" -> binding.toolbar.expandableToolbar.expandableToolbarComponent.menu.select(
                        R.id.songs
                    )
                    "Playlists" -> binding.toolbar.expandableToolbar.expandableToolbarComponent.menu.select(
                        R.id.playlist
                    )
                    "Favorite" -> binding.toolbar.expandableToolbar.expandableToolbarComponent.menu.select(
                        R.id.favorites
                    )
                }

            }
        })
    }
}