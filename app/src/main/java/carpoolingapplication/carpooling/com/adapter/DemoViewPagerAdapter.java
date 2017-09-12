package carpoolingapplication.carpooling.com.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import carpoolingapplication.carpooling.com.fragment.RegistrationFragment;

/**
 * Created by wardah on 9/11/17.
 */


public class DemoViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<RegistrationFragment> fragments = new ArrayList<>();
    private RegistrationFragment currentFragment;

    public DemoViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(RegistrationFragment.newInstance(0));
        fragments.add(RegistrationFragment.newInstance(1));
        fragments.add(RegistrationFragment.newInstance(2));
    }

    @Override
    public RegistrationFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((RegistrationFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public RegistrationFragment getCurrentFragment() {
        return currentFragment;
    }
}
