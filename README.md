# AndroidTemplate
This is template for Android app.

Problems:<br>
1.Error:Gradle DSL method not found: 'has()'<br>
Changing the line in Volley's bintray.gradle file from<br>
publish = project.has("release")<br>
to<br>
publish = project.hasProperty("release")<br>

2.Reset git<br>
git pull<br>
git add .<br>
git commit -m "add ignore"<br>
git push<br>
http://blog.csdn.net/lusyoe/article/details/53400948<br>

3.Gradle's dependency cache may be corrupt (this sometimes occurs after a network connection timeout.)<br>
AndroidTemplate\gradle\wrapper\gradle-wrapper.properties<br>
3.0 to 2.14.1<br>

4.Volley <br>
compile 'com.mcxiaoke.volley:library:1.0.6'<br>

5.Error:Configuration with name ‘default’ not found.<br>
Module need build.gradle<br>

Directions: <br>
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
