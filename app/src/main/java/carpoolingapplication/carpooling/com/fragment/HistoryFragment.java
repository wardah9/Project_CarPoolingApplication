package carpoolingapplication.carpooling.com.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import carpoolingapplication.carpooling.com.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    String titels[]={"Trip Nmae","Express Way","Public Road"};
    String des[]={"from Muscat to Omantel", "from Matrah to Muscat", "from Muscat to Matrah"};


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        ListView ls= (ListView) rootView.findViewById(R.id.list_card);
        ls.setAdapter(new customAdapter());

        return rootView;
    }

    class customAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titels.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater infator= (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
            View rootView=infator.inflate(R.layout.activity_card_view_sample,null);

            TextView tv=(TextView) rootView.findViewById(R.id.tv1);
            tv.setText(titels[position]);

            TextView tv2=(TextView) rootView.findViewById(R.id.tv2);
            tv2.setText(des[position]);

            return rootView;
        }
    }

}
