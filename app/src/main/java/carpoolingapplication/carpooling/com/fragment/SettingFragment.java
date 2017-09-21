package carpoolingapplication.carpooling.com.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

import carpoolingapplication.carpooling.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    Locale myLocal;
    String names[]={"English","Arabic"};

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_setting, container, false);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,names);

        ListView ls= (ListView) rootView.findViewById(R.id.language_list);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    changeLang("en");

                }

                if(position==1){
                    changeLang("ar");

                }

               // startActivity(new Intent(this,LangHomeActivity.class));


            }
        });

        return rootView;
    }



    public void changeLang(String lang){
        //if (lang.equalsIgnoreCase("")) return;

        myLocal=new Locale(lang);
        Locale.setDefault(myLocal);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale=myLocal;
        getContext().getResources().updateConfiguration(config,getContext().getResources().getDisplayMetrics());

    }

}
