/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dm.excellent.baselibrary.views.loadding;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dm.excellent.baselibrary.R;
import com.dm.excellent.baselibrary.views.SlackLoadingView;


public class CustomDialog extends Dialog {
    public static SlackLoadingView loading;

    public CustomDialog(Context context) {
        this(context, 0);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static CustomDialog instance(Context activity) {
        RelativeLayout v = (RelativeLayout) View.inflate(activity, R.layout.common_progress_view2, null);
        loading = (SlackLoadingView) v.findViewById(R.id.loading);
        loading.setLineLength(0.01f);
        CustomDialog dialog = new CustomDialog(activity, R.style.loading_dialog);
        dialog.setContentView(v,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );
        dialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                loading.reset();
            }
        });
        dialog.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                if (null != loading) {
                    loading.start();
                }
            }
        });
        return dialog;
    }

    public void start() {
        if (null != loading) {
            loading.start();
        }
    }

    public void stop() {
        if (null != loading) {
            loading.reset();
        }
    }
}
