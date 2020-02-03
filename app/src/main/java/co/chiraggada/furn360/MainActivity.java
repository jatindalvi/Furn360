package co.chiraggada.furn360;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.webianks.easy_feedback.EasyFeedback;

import java.util.HashMap;

import co.chiraggada.furn360.AR.ArActivity;
import co.chiraggada.furn360.fragments.DepartmentFragment;
import co.chiraggada.furn360.fragments.HomeFragment;
import co.chiraggada.furn360.fragments.ProfileFragment;
import co.chiraggada.furn360.fragments.WishlistFragment;
import co.chiraggada.furn360.networksync.CheckInternetConnection;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {


    private Drawer result;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;


    //to get user session data
    private UserSession session;
    private HashMap<String, String> user;
    private String name, email, photo, mobile;
    private String first_time;
    HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        TextView appname = findViewById(R.id.appname);
        appname.setTypeface(typeface);

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener);


        //retrieve session values and display on listviews
        getValues();

        //Navigation Drawer with toolbar
        inflateNavDrawer();

        homeFragment = new HomeFragment();
        openFragment(homeFragment, "HomeFragment");

        if (session.getFirstTime()) {
            //tap target view
//            tapview();
            session.setFirstTime(false);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                    HomeFragment homeFragment = new HomeFragment();
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        getSupportFragmentManager().popBackStack();
                    }
                    openFragment(homeFragment, "Home fragment");
                    return true;
                case R.id.navigation_cart:
                    Toast.makeText(MainActivity.this, "cart", Toast.LENGTH_SHORT).show();
                    DepartmentFragment departmentFragment = new DepartmentFragment();
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        getSupportFragmentManager().popBackStack();
                    }
                    openFragment(departmentFragment, "Department fragment");

                    return true;
                case R.id.navigation_wishlist:
                    Toast.makeText(MainActivity.this, "wishlist", Toast.LENGTH_SHORT).show();
                    WishlistFragment wishlistFragment = new WishlistFragment();
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        getSupportFragmentManager().popBackStack();
                    }
                    openFragment(wishlistFragment, "Wishlist fragment");
                    return true;
                case R.id.navigation_profile:
                    Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                    ProfileFragment profileFragment = new ProfileFragment();
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        getSupportFragmentManager().popBackStack();
                    }
                    openFragment(profileFragment, "profile fragment");
                    return true;

            }
            return false;
        }
    };

    private void tapview() {

        new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.notifintro), "Notifications", "Latest offers will be available here !")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.colorAccent)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.accent)
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.first),
                        TapTarget.forView(findViewById(R.id.view_profile), "Profile", "You can view and edit your profile here !")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.colorAccent)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.accent)
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
                                .outerCircleColor(R.color.third),
                        TapTarget.forView(findViewById(R.id.cart), "Your Cart", "Here is Shortcut to your cart !")
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextColor(R.color.colorAccent)
                                .titleTextSize(25)
                                .descriptionTextSize(15)
                                .descriptionTextColor(R.color.accent)
                                .drawShadow(true)
                                .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)
                                .transparentTarget(true)
//                                .outerCircleColor(R.color.second),
//                        TapTarget.forView(findViewById(R.id.visitingcards), "Categories", "Product Categories have been listed here !")
//                                .targetCircleColor(R.color.colorAccent)
//                                .titleTextColor(R.color.colorAccent)
//                                .titleTextSize(25)
//                                .descriptionTextSize(15)
//                                .descriptionTextColor(R.color.accent)
//                                .drawShadow(true)
//                                .cancelable(false)// Whether tapping outside the outer circle dismisses the view
//                                .tintTarget(true)
//                                .transparentTarget(true)
                                .outerCircleColor(R.color.fourth))
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        session.setFirstTime(false);
                        Toasty.success(MainActivity.this, " You are ready to go !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                }).start();

    }


    private void getValues() {

        //create new session object by passing application context
        session = new UserSession(getApplicationContext());

        //validating session
        session.isLoggedIn();

        //get User details if logged in
        user = session.getUserDetails();

        name = user.get(UserSession.KEY_NAME);
        email = user.get(UserSession.KEY_EMAIL);
        mobile = user.get(UserSession.KEY_MOBiLE);
        photo = user.get(UserSession.KEY_PHOTO);
    }

    private void inflateNavDrawer() {

        //set Custom toolbar to activity -----------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the AccountHeader ----------------------------------------------------------------

        //Profile Making
        IProfile profile = new ProfileDrawerItem()
                .withName(name)
                .withEmail(email)
                .withIcon(photo);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.gradient_background)
                .addProfiles(profile)
                .withCompactStyle(true)
                .build();

        //Adding nav drawer items ------------------------------------------------------------------
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.home).withIcon(R.drawable.home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.myprofile).withIcon(R.drawable.profile);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.wishlist).withIcon(R.drawable.wishlist);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.cart).withIcon(R.drawable.cart);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.logout).withIcon(R.drawable.logout);

        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(7).withName("Offers").withIcon(R.drawable.tag);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withIdentifier(8).withName(R.string.aboutapp).withIcon(R.drawable.credits);
        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withIdentifier(9).withName(R.string.feedback).withIcon(R.drawable.feedback);
        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withIdentifier(10).withName(R.string.helpcentre).withIcon(R.drawable.helpccenter);

        SecondaryDrawerItem item12 = new SecondaryDrawerItem().withIdentifier(12).withName("App Tour").withIcon(R.drawable.tour);
        SecondaryDrawerItem item13 = new SecondaryDrawerItem().withIdentifier(13).withName("Explore").withIcon(R.drawable.explore);


        //creating navbar and adding to the toolbar ------------------------------------------------
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withDrawerLayout(R.layout.crossfade_drawer)
                .withAccountHeader(headerResult)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        item1, item2, item3, item4, item5, new DividerDrawerItem(), item7, item8, item9, item10, new DividerDrawerItem(), item12, item13
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch (position) {

                            case 1:
                                if (result != null && result.isDrawerOpen()) {
                                    result.closeDrawer();
                                }
                                break;
                            case 2:
                                startActivity(new Intent(MainActivity.this, ArActivity.class));
                                break;
                            case 3:
                                startActivity(new Intent(MainActivity.this, IndividualProductActivity.class));
                                break;
                            case 4:
                                startActivity(new Intent(MainActivity.this, ArActivity.class));
                                break;
                            case 5:
                                session.logoutUser();
                                finish();
                                break;

                            case 7:
                                startActivity(new Intent(MainActivity.this, ArActivity.class));
                                break;

                            case 8:
                                Toast.makeText(MainActivity.this, "this not work", Toast.LENGTH_SHORT).show();
                                break;
                            case 9:
                                new EasyFeedback.Builder(MainActivity.this)
                                        .withEmail("beingdevofficial@gmail.com")
                                        .withSystemInfo()
                                        .build()
                                        .start();
                                break;
                            case 10:
                                startActivity(new Intent(MainActivity.this, ArActivity.class));
                                break;
                            case 12:
                                session.setFirstTimeLaunch(true);
                                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                                finish();
                                break;
                            case 13:
                                if (result != null && result.isDrawerOpen()) {
                                    result.closeDrawer();
                                }
                                tapview();
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "Default", Toast.LENGTH_LONG).show();

                        }

                        return true;
                    }
                })
                .build();

        //Setting crossfader drawer------------------------------------------------------------

        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));

        //add second view (which is the miniDrawer)
        final MiniDrawer miniResult = result.getMiniDrawer();

        //build the view for the MiniDrawer
        View view = miniResult.build(this);

        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));

        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });
    }


    @Override
    protected void onStop() {
//        sliderShow.stopAutoCycle();
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void viewProfile(View view) {
        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    public void viewCart(View view) {
        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    @Override
    protected void onResume() {

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
//        sliderShow.startAutoCycle();
        super.onResume();
    }

    public void Notifications(View view) {
        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    public void cardsActivity(View view) {
        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    public void tshirtActivity(View view) {
        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }


    public void bagsActivity(View view) {

        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    public void stationaryAcitivity(View view) {

        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    public void calendarsActivity(View view) {

        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    public void keychainsActivity(View view) {

        startActivity(new Intent(MainActivity.this, ArActivity.class));
    }

    public void openFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Log.d("Open Fragment", "Replacing the Fragment");
        ft.replace(R.id.FrameLayout, fragment, fragment.getTag());
        ft.addToBackStack(tag);
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("Back stack", "Back Stack count: " + count);
        ft.commit();
        getSupportFragmentManager().popBackStackImmediate("MainFragment", 0);
    }

}
