Congress API Android Project

AboutMe.java:

Displays student ID.

Bill.java, Committee.java, Legislators.java:

Java object to hold Bill/Committee/Legislators information.

BillAPI.java, CommitteeAPI.java, LegislatorAPI.java:

Used by Retrofit to request data from my PHP script on Google App Engine and parse data into Bill/Committee/Legislator object.

BillDetailsActivity.java, CommitteeDetailsActivity.java, LegislatorDetailsActivity.java:

Displays Bill/Committee/Legislator details.

BillFragment.java, CommitteeFragment.java, LegislatorFragment.java:

Uses TabLayout and ViewPager with custom adapter containing BillTabFragment/CommitteeTabFragment/LegislatorTabFragment objects for each tab.

BillResults.java, CommitteeResults.java, LegislatorResults.java:

Used when parsing Bill, Committee, Legislator data from JSON.

BillRowAdapter.java, CommitteeRowAdapter.java, LegislatorRowAdapter.java:

Used for displaying a row item in the RecyclerView Bill/Committee/Legislator list.

BillTabFragment.java, CommitteeTabFragment.java, LegislatorTabFragment.java:

Used to display different Bill/Committee/Legislator tabs. Requests data asynchronously using Retrofit. Sets RecyclerView list and adapter. In LegislatorTabFragment, my side index implementation is based on this tutorial: http://www.brightec.co.uk/ideas/android-listview-alphabet-scroller.

FavoriteFragment.java:

Uses TabLayout and ViewPager with custom adapter containing a LegislatorTabFragment, CommitteeTabFragment, and BillTabFragment.

BillResults.java, CommitteeResults.java, LegislatorResults.java, History.java, LastVersion.java, LegislatorResults.java, OcEmail.java, Page.java, Sponsor.java, Urls.java, Urls_.java:

Used for parsing Bill/Committee/Legislator from JSON.

MainActivity.java

Sets up main screen and creates new BillFragment.java, CommitteeFragment.java, LegislatorFragment.java, and FavoriteFragment, with LegislatorFragment as the starting fragment.

SharedPreference.java

Saves, adds, removes, and gets favorites for Bills/Committees/Legislators. My implementation is based on this tutorial: http://androidopentutorials.com/android-how-to-store-list-of-values-in-sharedpreferences/
