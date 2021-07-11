// Generated by view binder compiler. Do not edit!
package com.yangchoi.module_tab.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.yangchoi.module_tab.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySearchBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView btnDelete;

  @NonNull
  public final EditText editSearch;

  @NonNull
  public final RecyclerView historyRv;

  @NonNull
  public final RecyclerView hotKeyRv;

  @NonNull
  public final ImageView imgIcon;

  private ActivitySearchBinding(@NonNull LinearLayout rootView, @NonNull ImageView btnDelete,
      @NonNull EditText editSearch, @NonNull RecyclerView historyRv, @NonNull RecyclerView hotKeyRv,
      @NonNull ImageView imgIcon) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.editSearch = editSearch;
    this.historyRv = historyRv;
    this.hotKeyRv = hotKeyRv;
    this.imgIcon = imgIcon;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_delete;
      ImageView btnDelete = rootView.findViewById(id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.edit_search;
      EditText editSearch = rootView.findViewById(id);
      if (editSearch == null) {
        break missingId;
      }

      id = R.id.history_rv;
      RecyclerView historyRv = rootView.findViewById(id);
      if (historyRv == null) {
        break missingId;
      }

      id = R.id.hot_key_rv;
      RecyclerView hotKeyRv = rootView.findViewById(id);
      if (hotKeyRv == null) {
        break missingId;
      }

      id = R.id.img_icon;
      ImageView imgIcon = rootView.findViewById(id);
      if (imgIcon == null) {
        break missingId;
      }

      return new ActivitySearchBinding((LinearLayout) rootView, btnDelete, editSearch, historyRv,
          hotKeyRv, imgIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
