package pt.itector.itcarman.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import pt.itector.itcarman.R;
import pt.itector.itcarman.fragments.AddMovementFragment;


/**
 * Created by me on 01/08/2019
 */
public class AddNewMovementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.generic_fragmented_activity);

        getSupportActionBar().setTitle(getString(R.string.btn_add_new_movement));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fl, AddMovementFragment.newInstance());
        ft.commit();
    }
}
