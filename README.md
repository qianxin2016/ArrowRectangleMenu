# ArrowRectangleMenu
A customized ViewGroup which implements a round rectangle menu with a triangle arrow on top of it
![](https://github.com/qianxin2016/ArrowRectangleMenu/blob/master/ArrowRectangleMenu.png)
## Usage Example:
```Java
<?xml version="1.0" encoding="utf-8"?>
<com.xinxin.arrowrectanglemenu.widget.ArrowRectangleView
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:background="@android:color/transparent"
        android:paddingLeft="3dp"
        android:paddingRight="3dp"
        android:splitMotionEvents="false"
        app:arrow_offset="31dp"
        app:arrow_width="16dp"
        app:arrow_height="8dp"
        app:radius="5dp"
        app:background_color="#ffb1df83"
        app:shadow_color="#66000000"
        app:shadow_thickness="5dp">
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="42dp">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:textSize="16sp"
            android:textColor="#FF393F4A"
            android:paddingLeft="16dp"
            android:paddingRight="32dp"
            android:clickable="false"
            android:text="Menu Item #1"/>
    </LinearLayout>
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="42dp">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:textSize="16sp"
            android:textColor="#FF393F4A"
            android:paddingLeft="16dp"
            android:paddingRight="32dp"
            android:clickable="false"
            android:text="Menu Item #2"/>
    </LinearLayout>
</com.xinxin.arrowrectanglemenu.widget.ArrowRectangleView>
```
