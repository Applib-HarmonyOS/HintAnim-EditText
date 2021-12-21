/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tanfujun.hintanimedittext.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import com.example.tanfujun.hintanimedittext.ResourceTable;
import com.xiaochendev.lib.HintAnimEditText;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample app to test the HintAnimEditTest library functionality.
 */
public class MainAbilitySlice extends AbilitySlice {
    private static final HiLogLabel HILOG_LABEL = new HiLogLabel(0, 0, "MainAbilitySlice");
    private static final int DELAY_TIME = 3000;
    HintAnimEditText mEditText;
    Text mTextView;
    List<CharSequence> data;
    EventHandler handler = new EventHandler(EventRunner.getMainEventRunner());
    int mPos = 0;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        data = new ArrayList<>();
        data.add("abc@gmail.com");
        data.add("xyz@hotmail.com");
        data.add("mno@yahoo.com");

        mTextView = (Text) findComponentById(ResourceTable.Id_text_Signup);
        mEditText = (HintAnimEditText) findComponentById(ResourceTable.Id_Email);
        mTextView.setText("Sign Up");
        //Set the initial display
        try {
            mEditText.setHintString("Email");
            Runnable mrunnable = new Runnable() {
                @Override
                public void run() {
                    mEditText.changeHintWithAnim(data.get(mPos));
                    mPos++;
                    if (mPos >= data.size()) {
                        mPos = 0;
                    }
                    handler.postTask(this, DELAY_TIME);
                }
            };

            handler.postTask(mrunnable, DELAY_TIME);
        } catch (Exception e) {
            HiLog.error(HILOG_LABEL, "Exception in onStart");
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
