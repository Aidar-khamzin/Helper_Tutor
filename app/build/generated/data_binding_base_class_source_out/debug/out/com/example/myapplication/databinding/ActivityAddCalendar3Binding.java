// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import com.example.myapplication.R;
import info.hoang8f.widget.FButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddCalendar3Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircularProgressButton button10;

  @NonNull
  public final TextView currentDateTime;

  @NonNull
  public final FButton dateButton;

  @NonNull
  public final Spinner spinnerStudentC;

  @NonNull
  public final TextView textData;

  @NonNull
  public final TextView textStudentC;

  @NonNull
  public final TextView textTime;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textViewName;

  @NonNull
  public final FButton timeButton;

  private ActivityAddCalendar3Binding(@NonNull ConstraintLayout rootView,
      @NonNull CircularProgressButton button10, @NonNull TextView currentDateTime,
      @NonNull FButton dateButton, @NonNull Spinner spinnerStudentC, @NonNull TextView textData,
      @NonNull TextView textStudentC, @NonNull TextView textTime, @NonNull TextView textView,
      @NonNull TextView textViewName, @NonNull FButton timeButton) {
    this.rootView = rootView;
    this.button10 = button10;
    this.currentDateTime = currentDateTime;
    this.dateButton = dateButton;
    this.spinnerStudentC = spinnerStudentC;
    this.textData = textData;
    this.textStudentC = textStudentC;
    this.textTime = textTime;
    this.textView = textView;
    this.textViewName = textViewName;
    this.timeButton = timeButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddCalendar3Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddCalendar3Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_calendar3, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddCalendar3Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button10;
      CircularProgressButton button10 = ViewBindings.findChildViewById(rootView, id);
      if (button10 == null) {
        break missingId;
      }

      id = R.id.currentDateTime;
      TextView currentDateTime = ViewBindings.findChildViewById(rootView, id);
      if (currentDateTime == null) {
        break missingId;
      }

      id = R.id.dateButton;
      FButton dateButton = ViewBindings.findChildViewById(rootView, id);
      if (dateButton == null) {
        break missingId;
      }

      id = R.id.spinnerStudentC;
      Spinner spinnerStudentC = ViewBindings.findChildViewById(rootView, id);
      if (spinnerStudentC == null) {
        break missingId;
      }

      id = R.id.textData;
      TextView textData = ViewBindings.findChildViewById(rootView, id);
      if (textData == null) {
        break missingId;
      }

      id = R.id.textStudentC;
      TextView textStudentC = ViewBindings.findChildViewById(rootView, id);
      if (textStudentC == null) {
        break missingId;
      }

      id = R.id.textTime;
      TextView textTime = ViewBindings.findChildViewById(rootView, id);
      if (textTime == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textViewName;
      TextView textViewName = ViewBindings.findChildViewById(rootView, id);
      if (textViewName == null) {
        break missingId;
      }

      id = R.id.timeButton;
      FButton timeButton = ViewBindings.findChildViewById(rootView, id);
      if (timeButton == null) {
        break missingId;
      }

      return new ActivityAddCalendar3Binding((ConstraintLayout) rootView, button10, currentDateTime,
          dateButton, spinnerStudentC, textData, textStudentC, textTime, textView, textViewName,
          timeButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}