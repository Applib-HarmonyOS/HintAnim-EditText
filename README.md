[![.github/workflows/main.yml](https://github.com/applibgroup/HintAnim-EditText/actions/workflows/main.yml/badge.svg)](https://github.com/applibgroup/HintAnim-EditText/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=applibgroup_HintAnim-EditText&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=applibgroup_HintAnim-EditText)
# HintAnim-EditText

A HMOS library which provies Edittext with hint animation

## Source
Inspired by [cctanfujun/HintAnim-EditText](https://github.com/cctanfujun/HintAnim-EditText/) - version 0.1

## Feature
This library provides an animation of hint for EditText.

<img src="screenshots/HintAnimEditText.gif" width="350">

## Dependency
1. For using hintanimedittext module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
```groovy
	dependencies {
		implementation project(':hintanimedittext')
                implementation fileTree(dir: 'libs', include: ['*.har'])
                testImplementation 'junit:junit:4.13'
	}
```
2. For using hintanimedittext in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
```groovy
	dependencies {
		implementation fileTree(dir: 'libs', include: ['*.har'])
		testImplementation 'junit:junit:4.13'
	}
```
3. For using hintanimedittext from a remote repository in separate application, add the below dependencies in entry/build.gradle file.
```groovy
        dependencies {
            implementation 'dev.applibgroup:hintanimedittext:1.0.0' 
            testCompile 'junit:junit:4.13'
        }
```

## How TO USE

#### Declare in your xml
```xml
<com.xiaochendev.lib.HintAnimEditText
    ohos:id="$+id:Email"
    ohos:width="match_parent"
    ohos:height="70vp"
    ohos:element_left="$media:email"
    ohos:background_element="#FFD5A6E0"
    ohos:text_size="50vp"
    ohos:input_enter_key_type="enter_key_type_search"
    ohos:text_input_type="pattern_text"
    ohos:multiple_lines="false"
    ohos:bottom_margin="10vp"
    />
```

change hint with anim like this：

```java
mEditText.changeHintWithAnim("XXX");
```
Note: you should use ``setHintString("xxx")`` change hint without anim or ``changeHintWithAnim("XXX")`` change hint with anim instead of EditText’s hint.

## License
```
Copyright 2015 cctanfujun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.  
```
      
