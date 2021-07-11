# KotlinJetPackFramework

### 简介
    基于Android JetPack开发的项目,
    项目使用协程 和 retorfit封装网络,
    使用MVVM作为项目框架。
    
### 相关技术
    1.kotlin         [https://www.kotlincn.net/]
    2.mvvm           [https://github.com/Dawish/GoogleArchitectureDemo]
    3.viewbinding    [http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0811/3290.html]
    4.RxJava         [https://github.com/ReactiveX/RxJava]
    5.Retrofit       [https://square.github.io/retrofit/]
    6.Navigation     [https://developer.android.google.cn/jetpack/androidx/releases/navigation]
    7.Room           [https://developer.android.google.cn/jetpack/androidx/releases/room]
    8.MMKV           [https://github.com/tencent/mmkv]
    9.ARouter        [https://github.com/alibaba/ARouter/blob/master/README_CN.md]
    
    注:Navigation弃用,改成使用ARouter进行页面跳转。
    
    
### 网络封装
    使用Kotlin协程和retorfit封装网络。直接将请求托管给协程进行操作,配置正在请求的loading。接口访问步骤如下：
    1.ViewModel编写接收数据的数据源
        var bannerData = MutableLiveData<MutableList<BannerEntity>>()

    2.访问接口  参数一:{}  请求地址   参数二:T 接收数据的数据源  参数三:Boolean是否显示正在加载
        fun getBannerList(){
            launch({RequestUtil
                .getInstance()
                .getService()
                .getBanner()},bannerData,true)
        }

    3.在fragment或者activity的initView中使用vm请求接口
        vm.getBannerList()

    4.在fragment或者activity的initVM方法中给数据源赋值
        private var bannerList:MutableList<BannerEntity>? = null
        vm.bannerData.observe(this, Observer {bannerList = it})

### ARouter路由框架
    ARouter路由框架多用于module之间跳转。
    1.添加依赖和配置
    在每个module的build.gradle文件下添加，记住是每个！！！！
    android {
        defaultConfig {
            ...
            javaCompileOptions {
                annotationProcessorOptions {
                    arguments = [AROUTER_MODULE_NAME: project.getName()]
                }
            }
        }
    }
    添加依赖,这里有个骚东西    
    dependencies {
        //注意看官方的解释是下面这样子的：
        // 替换成最新版本, 需要注意的是api
        // 要与compiler匹配使用，均使用最新版可以保证兼容
        compile 'com.alibaba:arouter-api:x.x.x'
        annotationProcessor 'com.alibaba:arouter-compiler:x.x.x'
        ...
            
        //然后按照上面的方法在kotlin里面怎么都跳转不过去,会出现There no route matched! Path = xxxx的错误
        //这个就有点骚了,按照他的文档配置就是报错
        //emmmm  kotlin这样导入
        api LibsGitHub.ARoute
        kapt LibsGitHub.ARouteCompiler
        //api替换compile
        //kapt替换annotationProcessor 
        //如果是java 还是使用annotationProcessor进行导入
    }     
       
    2.创建路由管理类,统一管理路由配置
    object ARouterUrlUtil {
        //这里注意
        //1.必须是两级  而且是 / 开头 如：/xxx/xxx
        //2.如果从A module跳转到B module 那么一级路由不能相同，如 A： /aaa/xxx  B: /bbb/xxx
        const val DetailActivity = "/home/GoodsDetailActivity"
        const val JustToBuyActivity = "/classic/JustToBuyActivity"
    }
    
    3.基础跳转
    ARouter.getInstance().build(ARouterUrlUtil.JustToBuyActivity).navigation()
        
    4.携带参数跳转
    ARouter.getInstance().build(ARouterUrlUtil.JustToBuyActivity)
           .withLong("key1", 666L)
           .withString("key3", "888")
           .withObject("key4", new Test("Jack", "Rose"))
           .navigation(); 
                    
    5.通过@Autowired(name = "test")获取传递的值
         @Autowired(name = "test")
         val test:Int = 0
         
### MMKV本地信息缓存
    微信团队本地数据管理,弃用SharedPreferences。
    //获取kv实例
    val kv = MMKV.defaultMMKV()

    /**
     * @param dataType 保存数据的类型
     * @param dataKey 保存数据的key
     * @param dataValue 保存数据的value
     * */
    @JvmStatic
    fun saveValue(dataType: String,dataKey:String,dataValue:String){
        when(dataType){
            MMKVDataTypeMenu.BOOLEAN.toString() -> kv.encode(dataKey,dataValue.toBoolean())
            MMKVDataTypeMenu.INT.toString() -> kv.encode(dataKey,dataValue.toInt())
            MMKVDataTypeMenu.LONG.toString() -> kv.encode(dataKey,dataValue.toLong())
            MMKVDataTypeMenu.FLOAT.toString() -> kv.encode(dataKey,dataValue.toFloat())
            MMKVDataTypeMenu.DOUBLE.toString() -> kv.encode(dataKey,dataValue.toDouble())
            MMKVDataTypeMenu.STRING.toString() -> kv.encode(dataKey,dataValue)
            MMKVDataTypeMenu.ENTITY.toString() -> kv.encode(dataKey,dataValue)
        }
    }
    /**
     * @param dataType 数据类型
     *        boolean 布尔类型
     *        int     int
     *        long    长整形
     *        float   float
     *        double  double
     *        string  字符串
     *        entity  实体类  将实体类转成json字符串再进行保存
     * @param dataKey  保存的key
     * */
    fun getValue(dataType: String,dataKey:String):String{
        return when(dataType){
            MMKVDataTypeMenu.BOOLEAN.toString() -> "${kv.decodeBool(dataKey)}"
            MMKVDataTypeMenu.INT.toString() -> "${kv.decodeInt(dataKey)}"
            MMKVDataTypeMenu.LONG.toString() -> "${kv.decodeLong(dataKey)}"
            MMKVDataTypeMenu.FLOAT.toString() -> "${kv.decodeFloat(dataKey)}"
            MMKVDataTypeMenu.DOUBLE.toString() -> "${kv.decodeDouble(dataKey)}"
            MMKVDataTypeMenu.STRING.toString() -> kv.decodeString(dataKey)
            MMKVDataTypeMenu.ENTITY.toString() -> kv.decodeString(dataKey)
            else -> ""
        }
    }

### buildSrc
    采用Kotlin DLS的形式管理gradle版本信息、Android版本信息、第三方库版本信息。创建步骤如下：
     1.在根目录new-file创建一个lib文件夹,名字一定要是buildSrc这个名字
     2.编译的时候出现'buildSrc' cannot be used as a project name as it is a reserved name错误
       解决办法：删除掉setting.gradle文件里面的include buildSrc部分，重新编译

     3.删除除开src-main-java以外的所有文件
     4.在buildSrc文件夹下根目录创建build.gradle.kts文件，填充如下内容：
         plugins { `kotlin-dsl` }
         repositories { jcenter() }

     5.在包名下创建AndroidConfig文件管理app版本等信息，文件名字非固定
     6.在包名下创建GradleConfig文件管理gradle版本信息，文件名字非固定

     7.在包名下创建Version.kt文件管理第三方库版本
     8.在包名下创建Libs.kt文件管理依赖加载


### 引入第三方类库
    1.在Version.kt文件里面添加版本号
    object VersionAndroid{
        const val Junit = "4.+"
    }

    2.在Libs.kt文件里面完善引入路径
    object LibsAndroid {
        const val Junit = "junit:junit:${VersionAndroid.Junit}"
    }

    3.在对应的gradle文件使用
    testImplementation LibsAndroid.Junit

### 扩展至BaseActivity、BaseFragment
    * 扩展至Activity和Fragment都一直，直接继承BaseActiviy或者BaseFragment
    * 传入参数一: 对应的ViewModel  参数二:对应的布局
    * 布局的传入方式直接就是布局名称驼峰后面加上Binding,
        比如MainActivity对应布局是activity_main.xml那么对应的viewbinding就是ActivityMainBinding
    * 要想使用viewbinding必须在该module的gradle文件添加支持
    buildFeatures {
        viewBinding = true
    }
    class HomeFragment : BaseFragment<HomeFragmentVM, FragmentHomeBinding>()
    class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>()

### 扩展至BaseAdapter
    class HomeArticleAdapter(context:Activity,listData:MutableList<ArticleBean>)
        : BaseAdapter<ItemHomeArticleBinding,ArticleBean>(context,listData as ArrayList<ArticleBean>)

    参数解析:
    1.context:Activity 只能是Activity的上下文
    2.listData:MutableList<ArticleBean> 数据源  BaseAdapter接收的是ArrayList形式的，所以要进行转换
    3.ItemHomeArticleBinding 注入布局，和上面的activity、fragment一致
    4.ArticleBean 数据类型