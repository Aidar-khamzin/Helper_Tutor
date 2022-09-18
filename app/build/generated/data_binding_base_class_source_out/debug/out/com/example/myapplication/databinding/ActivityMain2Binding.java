// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMain2Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView ErrorText;

  @NonNull
  public final CircularProgressButton button4;

  @NonNull
  public final AppCompatButton buttonDelAddST;

  @NonNull
  public final EditText editAgeAddST;

  @NonNull
  public final EditText editLastNameAddST;

  @NonNull
  public final EditText editLessAddST;

  @NonNull
  public final EditText editNameAddST;

  @NonNull
  public final Spinner spinnerClassAddST;

  @NonNull
  public final TextView textAgeAddST;

  @NonNull
  public final TextView textClassAddST;

  @NonNull
  public final TextView textLastNameAddST;

  @NonNull
  public final TextView textLessAddST;

  @NonNull
  public final TextView textNameAddST;

  @NonNull
  public final TextView textViewName;

  private ActivityMain2Binding(@NonNull ConstraintLayout rootView, @NonNull TextView ErrorText,
      @NonNull CircularProgressButton button4, @NonNull AppCompatButton buttonDelAddST,
      @NonNull EditText editAgeAddST, @NonNull EditText editLastNameAddST,
      @NonNull EditText editLessAddST, @NonNull EditText editNameAddST,
      @NonNull Spinner spinnerClassAddST, @NonNull TextView textAgeAddST,
      @NonNull TextView textClassAddST, @NonNull TextView textLastNameAddST,
      @NonNull TextView textLessAddST, @NonNull TextView textNameAddST,
      @NonNull TextView textViewName) {
    this.rootView = rootView;
    this.ErrorText = ErrorText;
    this.button4 = button4;
    this.buttonDelAddST = buttonDelAddST;
    this.editAgeAddST = editAgeAddST;
    this.editLastNameAddST = editLastNameAddST;
    this.editLessAddST = editLessAddST;
    this.editNameAddST = editNameAddST;
    this.spinnerClassAddST = spinnerClassAddST;
    this.textAgeAddST = textAgeAddST;
    this.textClassAddST = textClassAddST;
    this.textLastNameAddST = textLastNameAddST;
    this.textLessAddST = textLessAddST;
    this.textNameAddST = textNameAddST;
    this.textViewName = textViewName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMain2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ErrorText;
      TextView ErrorText = ViewBindings.findChildViewById(rootView, id);
      if (ErrorText == null) {
        break missingId;
      }

      id = R.id.button4;
      CircularProgressButton button4 = ViewBindings.findChildViewById(rootView, id);
      if (button4 == null) {
        break missingId;
      }

      id = R.id.buttonDelAddST;
      AppCompatButton buttonDelAddST = ViewBindings.findChildViewById(rootView, id);
      if (buttonDelAddST == null) {
        break missingId;
      }

      id = R.id.editAgeAddST;
      EditText editAgeAddST = ViewBindings.findChildViewById(rootView, id);
      if (editAgeAddST == null) {
        break missingId;
      }

      id = R.id.editLastNameAddST;
      EditText editLastNameAddST = ViewBindings.findChildViewById(rootView, id);
      if (editLastNameAddST == null) {
        break missingId;
      }

      id = R.id.editLessAddST;
      EditText editLessAddST = ViewBindings.findChildViewById(rootView, id);
      if (editLessAddST == null) {
        break missingId;
      }

      id = R.id.editNameAddST;
      EditText editNameAddST = ViewBindings.findChildViewById(rootView, id);
      if (editNameAddST == null) {
        break missingId;
      }

      id = R.id.spinnerClassAddST;
      Spinner spinnerClassAddST = ViewBindings.findChildViewById(rootView, id);
      if (spinnerClassAddST == null) {
        break missingId;
      }

      id = R.id.textAgeAddST;
      TextView textAgeAddST = ViewBindings.findChildViewById(rootView, id);
      if (textAgeAddST == null) {
        break missingId;
      }

      id = R.id.textClassAddST;
      TextView textClassAddST = ViewBindings.findChildViewById(rootView, id);
      if (textClassAddST == null) {
        break missingId;
      }

      id = R.id.textLastNameAddST;
      TextView textLastNameAddST = ViewBindings.findChildViewById(rootView, id);
      if (textLastNameAddST == null) {
        break missingId;
      }

      id = R.id.textLessAddST;
      TextView textLessAddST = ViewBindings.findChildViewById(rootView, id);
      if (textLessAddST == null) {
        break missingId;
      }

      id = R.id.textNameAddST;
      TextView textNameAddST = ViewBindings.findChildViewById(rootView, id);
      if (textNameAddST == null) {
        break missingId;
      }

      id = R.id.textViewName;
      TextView textViewName = ViewBindings.findChildViewById(rootView, id);
      if (textViewName == null) {
        break missingId;
      }

      return new ActivityMain2Binding((ConstraintLayout) rootView, ErrorText, button4,
          buttonDelAddST, editAgeAddST, editLastNameAddST, editLessAddST, editNameAddST,
          spinnerClassAddST, textAgeAddST, textClassAddST, textLastNameAddST, textLessAddST,
          textNameAddST, textViewName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}