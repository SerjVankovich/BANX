package com.example.sergey.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.BankCard;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.GlobalFunctions;
import com.example.sergey.myapplication.MainActivity;
import com.example.sergey.myapplication.MapActivity;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.SiteActivity;
import com.example.sergey.myapplication.fragments.FragmentVklads;
import com.example.sergey.myapplication.fragments.FragmentVkladsFromBanx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 25.02.2018.
 */

public class ResBankAdapter extends RecyclerView.Adapter<ResBankAdapter.ViewHolder> {
    private List<BankCard> main_array;
    ViewBinderHelper vhelper;
    FragmentTransaction transaction;
    Context context;

    public ResBankAdapter(List<BankCard> main_array, FragmentTransaction transaction, Context context) {
        this.main_array = main_array;
        this.transaction = transaction;
        vhelper = new ViewBinderHelper();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);


        holder.vkladsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FragmentVkladsFromBanx fvklads = new FragmentVkladsFromBanx();
                fvklads.setFirstChild("all_vklads");
                fvklads.setSecondChild("vklads");
                fvklads.setBankName(main_array.get(position).bank);
                transaction.addToBackStack(null);
                transaction.setCustomAnimations(R.animator.fragment_left_anim, R.animator.fragment_right_anim, R.animator.fragment_left_anim, R.animator.fragment_right_anim);
                transaction.replace(R.id.container, fvklads).commit();
            }
        });
        holder.creditsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FragmentVkladsFromBanx fvklads = new FragmentVkladsFromBanx();
                fvklads.setFirstChild("all_kredits");
                fvklads.setSecondChild("kredits");
                fvklads.setBankName(main_array.get(position).bank);
                transaction.addToBackStack(null);
                transaction.setCustomAnimations(R.animator.fragment_left_anim, R.animator.fragment_right_anim, R.animator.fragment_left_anim, R.animator.fragment_right_anim);
                transaction.replace(R.id.container, fvklads).commit();
            }
        });
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, SiteActivity.class);
                intent.putExtra("link", translateIntoURL(main_array.get(position).bank));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("bankName", main_array.get(position).bank);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.creditsNum.setText(main_array.get(position).len_cred);
        holder.vkladsNum.setText(main_array.get(position).len_vklads);
        findImage(holder.bankName, holder.icon, main_array, position, 120, 120);
        vhelper.bind(holder.bankCard, main_array.get(position).toString());
        vhelper.setOpenOnlyOne(true);

    }

    @Override
    public int getItemCount() {
        return main_array.size();
    }
    public void findImage(TextView bankName, ImageView imageView, List<BankCard> list, int position, int width, int height){
        String bank = list.get(position).bank;
        switch (bank){
            case "alfabank":
                Picasso.get().load(R.drawable.alfabank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Альфабанк");
                break;
            case "atb":
                Picasso.get().load(R.drawable.atb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("АТБ");
                break;
            case "baikalinvestbank":
                Picasso.get().load(R.drawable.baikalinvestbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Байкал Инвест Банк");
                break;
            case "bbrbank":
                Picasso.get().load(R.drawable.bbrbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("ББР Банк");
                break;
            case "binbank":
                Picasso.get().load(R.drawable.binbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Бинбанк");
                break;
            case "dalnevostochny":
                Picasso.get().load(R.drawable.dalnevostochny).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Дальневосточный");
                break;
            case "gazprombank":
                Picasso.get().load(R.drawable.gazprombank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Газпромбанк");
                break;
            case "homecreditbank":
                Picasso.get().load(R.drawable.homecreditbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Хоум Кредит");
                break;
            case "mosoblbank":
                Picasso.get().load(R.drawable.mosoblbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Мособлбанк");
                break;
            case "mts-bank":
                Picasso.get().load(R.drawable.mts_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("МТС-Банк");
                break;
            case "otkritie":
                Picasso.get().load(R.drawable.otkritie).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("ФК Открытие");
                break;
            case "pochtabank":
                Picasso.get().load(R.drawable.pochtabank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Почтабанк");
                break;
            case "primorye":
                Picasso.get().load(R.drawable.primorye).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Приморье");
                break;
            case "primsotsbank":
                Picasso.get().load(R.drawable.primsotsbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Примсоцбанк");
                break;
            case "promsvyazbank":
                Picasso.get().load(R.drawable.promsvyazbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Промсвязьбанк");
                break;
            case "ptkb":
                Picasso.get().load(R.drawable.ptkb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Примтеркомбанк");
                break;
            case "rgsbank":
                Picasso.get().load(R.drawable.rgsbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Росгосстрах Банк");
                break;
            case "rosbank":
                Picasso.get().load(R.drawable.rosbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Росбанк");
                break;
            case "roscap":
                Picasso.get().load(R.drawable.roscap).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Россиийский Капитал");
                break;
            case "rsb":
                Picasso.get().load(R.drawable.rsb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Русский Стандарт");
                break;
            case "rshb":
                Picasso.get().load(R.drawable.rshb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Россельхозбанк");
                break;
            case "rusfinancebank":
                Picasso.get().load(R.drawable.rusfinancebank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Русфинанс Банк");
                break;
            case "sberbank":
                Picasso.get().load(R.drawable.sberbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Сбербанк");
                break;
            case "skb-bank":
                Picasso.get().load(R.drawable.skb_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("СКБ-Банк");
                break;
            case "sovcombank":
                Picasso.get().load(R.drawable.sovcombank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Совкомбанк");
                break;
            case "sviaz-bank":
                Picasso.get().load(R.drawable.sviaz_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Связьбанк");
                break;
            case "tinkoff":
                Picasso.get().load(R.drawable.tinkoff).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Тинькофф");
                break;
            case "ussury":
                Picasso.get().load(R.drawable.ussury).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Уссури");
                break;
            case "v-express-bank":
                Picasso.get().load(R.drawable.v_express_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Восточный экспресс банк");
                break;
            case "vtb":
                Picasso.get().load(R.drawable.vtb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк ВТБ");
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        SwipeRevealLayout bankCard;
        TextView bankName;
        TextView vkladsNum;
        TextView creditsNum;
        ImageView icon;
        Button vkladsBtn;
        Button creditsBtn;
        ImageButton mapButton;
        RelativeLayout root;
        public ViewHolder(View itemView) {
            super(itemView);
            vkladsBtn = itemView.findViewById(R.id.vklads_btn);
            creditsBtn = itemView.findViewById(R.id.credits_btn);
            mapButton = itemView.findViewById(R.id.mapButton);
            bankCard = itemView.findViewById(R.id.card_view);
            bankName = itemView.findViewById(R.id.BankName);
            vkladsNum = itemView.findViewById(R.id.bank_fragment_vklads);
            creditsNum = itemView.findViewById(R.id.bank_fragment_credits);
            icon = itemView.findViewById(R.id.bankIcon);
            root = itemView.findViewById(R.id.root);
        }
    }
    public String translateIntoURL(String name) {
        switch (name){
            case "alfabank":
                return "https://alfabank.ru/";
            case "atb":
                return "https://www.atb.su/";
                case "baikalinvestbank":
                return "http://www.baikalinvestbank.ru/";
            case "bbrbank":
                return "https://bbr.ru/";
            case "binbank":
                return "https://www.binbank.ru/";

            case "dalnevostochny":
                return "https://www.dvbank.ru/";

            case "gazprombank":
               return "http://www.gazprombank.ru/";

            case "homecreditbank":
                return "https://www.homecredit.ru/";

            case "mosoblbank":
                return "http://mosoblbank.ru/";

            case "mts-bank":
                return "https://www.mtsbank.ru/";

            case "otkritie":
                return "https://www.open.ru/";

            case "pochtabank":
                return "https://www.pochtabank.ru/";

            case "primorye":
                return "https://www.primbank.ru/";

            case "primsotsbank":
                return "https://www.pskb.com/";

            case "promsvyazbank":
                return "https://www.psbank.ru/";

            case "ptkb":
               return "http://www.ptkb.ru/";

            case "rgsbank":
                return "https://www.rgsbank.ru/";

            case "rosbank":
                return "http://www.rosbank.ru/";

            case "roscap":
                return "https://www.roscap.ru/";

            case "rsb":
                return "https://www.rsb.ru/";

            case "rshb":
                return "https://www.rshb.ru/";

            case "rusfinancebank":
                return "https://www.rusfinancebank.ru/";

            case "sberbank":
                return "https://www.sberbank.ru/ru/person";

            case "skb-bank":
                return "http://www.skbbank.ru/";

            case "sovcombank":
                return "https://sovcombank.ru/";

            case "sviaz-bank":
                return "https://www.sviaz-bank.ru/";

            case "tinkoff":
                return "https://www.tinkoff.ru/";

            case "ussury":
                return "https://vlad.ussurybank.ru/";

            case "v-express-bank":
                return "https://www.vostbank.ru/";

            case "vtb":
                return "https://www.vtb.ru/";

        }
        return null;
    }
}
