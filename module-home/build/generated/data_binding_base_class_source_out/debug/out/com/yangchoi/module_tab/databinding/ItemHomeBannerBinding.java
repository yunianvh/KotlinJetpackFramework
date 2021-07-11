// Generated by view binder compiler. Do not edit!
package com.yangchoi.module_tab.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.yangchoi.module_tab.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemHomeBannerBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView bannerImage;

  private ItemHomeBannerBinding(@NonNull LinearLayout rootView, @NonNull ImageView bannerImage) {
    this.rootView = rootView;
    this.bannerImage = bannerImage;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemHomeBannerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemHomeBannerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_home_banner, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemHomeBannerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.banner_image;
      ImageView bannerImage = rootView.findViewById(id);
      if (bannerImage == null) {
        break missingId;
      }

      return new ItemHomeBannerBinding((LinearLayout) rootView, bannerImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
