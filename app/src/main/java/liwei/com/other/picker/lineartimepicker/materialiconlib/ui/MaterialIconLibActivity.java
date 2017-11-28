package liwei.com.other.picker.lineartimepicker.materialiconlib.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.GridView;

import java.util.Arrays;
import java.util.List;

import liwei.com.R;
import liwei.com.other.picker.lineartimepicker.materialiconlib.MaterialDrawableBuilder;
import liwei.com.other.picker.lineartimepicker.materialiconlib.MaterialMenuInflater;

/**
 * material_icon_lib，icon列表集合Activity
 */
public class MaterialIconLibActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_icon_lib);
        initViews();
    }

    private void initViews() {
        GridView mListview = (GridView) findViewById(R.id.listview);
        List<MaterialDrawableBuilder.IconValue> vals = Arrays.asList(MaterialDrawableBuilder.IconValue.values());

        ImageAdapter adapt = new ImageAdapter(vals);
        mListview.setAdapter(adapt);

        final Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar_1);
        MaterialMenuInflater.with(toolbar1.getContext(), new SupportMenuInflater(toolbar1.getContext()))
                .inflate(R.menu.menu_nocolor, toolbar1.getMenu());

        // Activity Theme materialIconColor attribute (lowest priority default color)
        // View specific Theme e.g. app:theme="..."   (2nd lowest in priority)
        // setDefaultColor(Resource) methods (highest priority default color)
        // app:materialIconColor set on an <item> tag in the menu XML file (overrides any other color choice)

        MaterialMenuInflater
                .with(this)
                .setDefaultColor(Color.BLUE)
                .inflate(R.menu.menu_nocolor, toolbar1.getMenu());

        /*
        setContentView(R.layout.circletest);
        ((CircleImageView)findViewById(R.id.profile_image)).setImageDrawable(
                MaterialDrawableBuilder.with(this)
                        .setSizeDp(96)
                        .setIcon(MaterialDrawableBuilder.IconValue.FACEBOOK_BOX)
                        .setColor(Color.BLUE)
                        .build()
        );
        */


        /*mIcon = (MaterialIconView) findViewById(R.id.icon);
        ImageView imgicon = (ImageView) findViewById(R.id.image_icon);

        mIcon.setIcon(MaterialDrawableBuilder.IconValue.CONTENT_COPY);
        imgicon.setImageDrawable(
                MaterialDrawableBuilder.with(this)
                        .setColor(Color.WHITE)
                        .setToActionbarSize()
                        .setIcon(MaterialDrawableBuilder.IconValue.AMAZON_CLOUDDRIVE)
                        .build()
        );
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MaterialMenuInflater.with(this).inflate(R.menu.menu_main, menu);
        return true;
    }
}