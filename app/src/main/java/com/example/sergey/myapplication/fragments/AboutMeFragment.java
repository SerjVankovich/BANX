package com.example.sergey.myapplication.fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergey.myapplication.R;
import com.squareup.picasso.Picasso;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class AboutMeFragment extends Fragment {
    private AboutBuilder builder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        builder = AboutBuilder.with(getContext())
                .setAppIcon(R.mipmap.icon_launcher)
                .setAppName("BANX")
                .setPhoto(R.drawable.my_portrait)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName(getString(R.string.dev_name))
                .setSubTitle(getString(R.string.subtitle))
                .setLinksColumnsCount(3)
                .setBrief(getString(R.string.about_banx))
                .addLink(R.mipmap.telegram_logo, "Telegram", "https://t.me/Sergey_Vankovich")
                .addLink(R.mipmap.vk_logo, "VK", "https://vk.com/id213328218")
                .addEmailLink("serzh.anchous@gmail.com")
                .addWhatsappLink("Сергей", "+79024862707")
                .addGitHubLink("SerjVankovich")
                .addFacebookLink("sergeyvankovich")
                .setVersionNameAsAppSubTitle()
                .setActionsColumnsCount(2)
                .setShowAsCard(false)
                .addShareAction("BANX")
                .addFeedbackAction("serzh.anchous@gmail.com")
                .setWrapScrollView(true);

        return builder.build();
    }
}
