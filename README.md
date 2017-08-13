# AndroidTemplate
This is template for Android app.

Problems:
1.Error:Gradle DSL method not found: 'has()'
Changing the line in Volley's bintray.gradle file from
publish = project.has("release")
to
publish = project.hasProperty("release")

2.Reset git
git pull
git add .
git commit -m "add ignore"
git push
http://blog.csdn.net/lusyoe/article/details/53400948

3.Gradle's dependency cache may be corrupt (this sometimes occurs after a network connection timeout.)
AndroidTemplate\gradle\wrapper\gradle-wrapper.properties
3.0 to 2.14.1

4.Volley 
compile 'com.mcxiaoke.volley:library:1.0.6'

5.Error:Configuration with name ‘default’ not found.
Module need build.gradle

Directions: 
<a target="_blank" href="http://lizheblogs.com/all/code/2016/06/12/android-template.html">
http://lizheblogs.com/all/code/2016/06/12/android-template.html</a></p>

## License

    Copyright 2016 Li Zhe <pulqueli@gmail.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
