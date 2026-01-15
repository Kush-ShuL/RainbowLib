# RainbowUtils - Java开发工具包

一个简化Java开发的工具包，提供打印、JSON和YAML处理功能。

## 安装

### 📦 JitPack（推荐）

**Gradle (Kotlin DSL)**
```kotlin
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Kush-ShuL:RainbowLib:1.0.1'
}
```

**Gradle (Groovy)**
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
        implementation 'com.github.Kush-ShuL:RainbowLib:1.0.1'
}
```

**Maven**
```xml
<repositories>
    <repository>
        <id>jitpack</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Kush-ShuL</groupId>
        <artifactId>RainbowLib</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>
```

### 📥 直接下载JAR

从 [GitHub Releases](https://github.com/Kush-ShuL/RainbowLib/releases) 下载JAR文件，添加到项目的classpath中。

## 功能特性

### 🖨️ 打印功能 (Messages.Print)
提供带时间戳的不同级别打印功能：
- `Print.info(String message)` - 信息日志
- `Print.warn(String message)` - 警告日志  
- `Print.error(String message)` - 错误日志
- `Print.debug(String message)` - 调试日志
- `Print.plain(String/Object)` - 普通打印

### ⌨️ 输入功能 (Messages.Input)
解决Scanner使用不便的多功能输入类，支持带提示的输入：
- `Input.string(String prompt)` - 字符串输入（带提示）
- `Input.string()` - 字符串输入（无提示）
- `Input.integer(String prompt)` - 整数输入（自动验证）
- `Input.longNum(String prompt)` - 长整数输入（自动验证）
- `Input.decimal(String prompt)` - 双精度浮点数输入（自动验证）
- `Input.floatNum(String prompt)` - 单精度浮点数输入（自动验证）
- `Input.bool(String prompt)` - 布尔值输入（支持y/n/yes/no等）
- `Input.character(String prompt)` - 字符输入
- `Input.select(String prompt, String... options)` - 选择输入（菜单式）
- `Input.confirm(String prompt, String defaultValue)` - 确认输入（带默认值）
- `Input.password(String prompt)` - 密码输入（支持隐藏）
- `Input.multiLine(String prompt, String endKeyword)` - 多行输入
- `Input.stringList(String prompt, String delimiter)` - 列表输入（分隔符解析）
- `Input.close()` - 关闭Scanner

### 📝 日志系统 (Logger)
简单易用的日志系统，支持多级别日志输出：
- `LoggerFactory.getLogger(String name)` - 获取指定名称的Logger
- `LoggerFactory.getLogger(Class<?> clazz)` - 获取指定类的Logger
- `LoggerFactory.getLogger()` - 自动获取调用类的Logger
- `Logger.trace(String message)` - TRACE级别日志
- `Logger.debug(String message)` - DEBUG级别日志
- `Logger.info(String message)` - INFO级别日志
- `Logger.warn(String message)` - WARN级别日志
- `Logger.error(String message)` - ERROR级别日志
- `Logger.setLevel(LogLevel level)` - 设置日志级别
- `LoggerFactory.setGlobalLevel(LogLevel level)` - 设置全局日志级别
- 支持异常堆栈跟踪输出
- 自动时间戳和线程信息

### 📄 JSON处理 (Configs.Json)
完整的JSON读写和处理功能：
- `Json.writeToFile(Object obj, String filePath)` - 写入JSON文件
- `Json.readFromFile(String filePath, Class<T> clazz)` - 从JSON文件读取
- `Json.toJsonString(Object obj)` - 对象转JSON字符串
- `Json.fromJsonString(String jsonString, Class<T> clazz)` - JSON字符串转对象
- `Json.toMap(Object obj)` - 对象转Map
- `Json.fromMap(Map<String, Object> map, Class<T> clazz)` - Map转对象

### 📝 YAML处理 (Configs.Yaml)
完整的YAML读写和处理功能：
- `Yaml.writeToFile(Object obj, String filePath)` - 写入YAML文件
- `Yaml.readFromFile(String filePath, Class<T> clazz)` - 从YAML文件读取
- `Yaml.toYamlString(Object obj)` - 对象转YAML字符串
- `Yaml.fromYamlString(String yamlString, Class<T> clazz)` - YAML字符串转对象
- `Yaml.toMap(Object obj)` - 对象转Map
- `Yaml.fromMap(Map<String, Object> map, Class<T> clazz)` - Map转对象
- `Yaml.convertJsonToYaml(String jsonFilePath, String yamlFilePath)` - JSON转YAML
- `Yaml.convertYamlToJson(String yamlFilePath, String jsonFilePath)` - YAML转JSON

### ⚙️ Properties处理 (Configs.Properties)
Java Properties文件处理功能：
- `Properties.writeToFile(Map<String, String> data, String filePath)` - 写入Properties文件
- `Properties.readFromFile(String filePath)` - 从Properties文件读取
- `Properties.readFromFileAsMap(String filePath)` - 从Properties文件读取为Map
- `Properties.getProperty(String filePath, String key)` - 获取属性值
- `Properties.getProperty(String filePath, String key, String defaultValue)` - 获取属性值（带默认值）
- `Properties.setProperty(String filePath, String key, String value)` - 设置属性值
- `Properties.getPropertyNames(String filePath)` - 获取所有属性名
- `Properties.containsKey(String filePath, String key)` - 检查属性是否存在
- `Properties.removeProperty(String filePath, String key)` - 删除属性

### 📋 TOML处理 (Configs.Toml)
TOML配置文件处理功能：
- `Toml.writeToFile(Object obj, String filePath)` - 写入TOML文件
- `Toml.readFromFile(String filePath, Class<T> clazz)` - 从TOML文件读取
- `Toml.toTomlString(Object obj)` - 对象转TOML字符串
- `Toml.fromTomlString(String tomlString, Class<T> clazz)` - TOML字符串转对象
- `Toml.toMap(Object obj)` - 对象转Map
- `Toml.fromMap(Map<String, Object> map, Class<T> clazz)` - Map转对象
- `Toml.convertJsonToToml(String jsonFilePath, String tomlFilePath)` - JSON转TOML
- `Toml.convertTomlToJson(String tomlFilePath, String jsonFilePath)` - TOML转JSON
- `Toml.convertYamlToToml(String yamlFilePath, String tomlFilePath)` - YAML转TOML
- `Toml.convertTomlToYaml(String tomlFilePath, String yamlFilePath)` - TOML转YAML

### 📄 CFG处理 (Configs.Cfg)
简单配置文件（键值对格式）处理功能：
- `Cfg.writeToFile(Map<String, String> data, String filePath)` - 写入CFG文件
- `Cfg.readFromFile(String filePath)` - 从CFG文件读取
- `Cfg.getValue(String filePath, String key)` - 获取配置值
- `Cfg.getValue(String filePath, String key, String defaultValue)` - 获取配置值（带默认值）
- `Cfg.setValue(String filePath, String key, String value)` - 设置配置值
- `Cfg.containsKey(String filePath, String key)` - 检查配置是否存在
- `Cfg.removeKey(String filePath, String key)` - 删除配置项
- `Cfg.addComment(String filePath, String comment)` - 添加注释
- `Cfg.readSection(String filePath, String sectionPrefix)` - 读取配置段

### 🏷️ XML处理 (Configs.Xml)
XML配置文件处理功能：
- `Xml.writeToFile(Object obj, String filePath)` - 写入XML文件
- `Xml.readFromFile(String filePath, Class<T> clazz)` - 从XML文件读取
- `Xml.toXmlString(Object obj)` - 对象转XML字符串
- `Xml.fromXmlString(String xmlString, Class<T> clazz)` - XML字符串转对象
- `Xml.toMap(Object obj)` - 对象转Map
- `Xml.fromMap(Map<String, Object> map, Class<T> clazz)` - Map转对象
- `Xml.convertJsonToXml(String jsonFilePath, String xmlFilePath)` - JSON转XML
- `Xml.convertXmlToJson(String xmlFilePath, String jsonFilePath)` - XML转JSON
- `Xml.convertYamlToXml(String yamlFilePath, String xmlFilePath)` - YAML转XML
- `Xml.convertXmlToYaml(String xmlFilePath, String yamlFilePath)` - XML转YAML
- `Xml.convertTomlToXml(String tomlFilePath, String xmlFilePath)` - TOML转XML
- `Xml.convertXmlToToml(String xmlFilePath, String tomlFilePath)` - XML转TOML

### 🎮 NBT处理 (Configs.Nbt)
Minecraft NBT文件处理功能：
- `Nbt.writeToFile(Map<String, Object> data, String filePath)` - 写入NBT文件
- `Nbt.writeToFile(Map<String, Object> data, String filePath, boolean compressed)` - 写入NBT文件（可指定压缩）
- `Nbt.readFromFile(String filePath)` - 从NBT文件读取
- `Nbt.readFromFile(String filePath, boolean compressed)` - 从NBT文件读取（可指定压缩）
- `Nbt.readFromFile(String filePath, boolean compressed, String rootName)` - 从NBT文件读取（指定根标签名）
- 支持所有NBT标签类型：Byte, Short, Int, Long, Float, Double, ByteArray, String, List, Compound, IntArray, LongArray
- 支持GZIP压缩的NBT文件
- 完整的Minecraft数据结构支持

### 🔢 算法工具包 (Algorithms)
常用算法和数据结构处理工具：

#### 排序算法 (Algorithms.Sort)
- `Sort.bubbleSort()` - 冒泡排序 O(n²)
- `Sort.selectionSort()` - 选择排序 O(n²)
- `Sort.insertionSort()` - 插入排序 O(n²)
- `Sort.mergeSort()` - 归并排序 O(n log n)
- `Sort.quickSort()` - 快速排序 O(n log n)
- `Sort.heapSort()` - 堆排序 O(n log n)
- `Sort.shellSort()` - 希尔排序 O(n^(3/2))
- `Sort.countingSort()` - 计数排序 O(n+k)
- `Sort.radixSort()` - 基数排序 O(d*(n+k))
- `Sort.bucketSort()` - 桶排序 O(n+k)
- `Sort.timSort()` - Tim排序 O(n log n)
- `Sort.isSorted()` - 检查数组是否已排序
- `Sort.getSortingStats()` - 获取排序性能统计

#### 搜索算法 (Algorithms.Search)
- `Search.linearSearch()` - 线性搜索 O(n)
- `Search.binarySearch()` - 二分搜索 O(log n)
- `Search.jumpSearch()` - 跳跃搜索 O(√n)
- `Search.interpolationSearch()` - 插值搜索 O(log log n)
- `Search.exponentialSearch()` - 指数搜索 O(log n)
- `Search.ternarySearch()` - 三分搜索 O(log n)
- `Search.findAllOccurrences()` - 查找所有出现位置
- `Search.kthSmallest()` - 查找第k小元素
- `Search.searchInRotated()` - 在旋转数组中搜索
- `Search.findPeak()` - 查找峰值元素
- `Search.compareSearchAlgorithms()` - 搜索算法性能比较

#### 数学算法 (Algorithms.MathUtils)
- `MathUtils.gcd()` - 最大公约数
- `MathUtils.lcm()` - 最小公倍数
- `MathUtils.power()` - 快速幂运算
- `MathUtils.modPower()` - 模幂运算
- `MathUtils.isPrime()` - 质数检查
- `MathUtils.sieveOfEratosthenes()` - 埃拉托斯特尼筛法
- `MathUtils.primeFactorization()` - 质因数分解
- `MathUtils.fibonacci()` - 斐波那契数列
- `MathUtils.factorial()` - 阶乘
- `MathUtils.combination()` - 组合数
- `MathUtils.permutation()` - 排列数
- `MathUtils.average()` - 平均值
- `MathUtils.median()` - 中位数
- `MathUtils.standardDeviation()` - 标准差
- `MathUtils.isPerfectSquare()` - 完全平方数检查
- `MathUtils.isPowerOfTwo()` - 2的幂检查

#### 字符串算法 (Algorithms.StringUtils)
- `StringUtils.reverse()` - 字符串反转
- `StringUtils.isPalindrome()` - 回文检查
- `StringUtils.removeDuplicates()` - 去除重复字符
- `StringUtils.areAnagrams()` - 变位词检查
- `StringUtils.longestCommonPrefix()` - 最长公共前缀
- `StringUtils.longestCommonSubstring()` - 最长公共子串
- `StringUtils.permutations()` - 全排列生成
- `StringUtils.levenshteinDistance()` - 编辑距离
- `StringUtils.firstNonRepeatingChar()` - 第一个不重复字符
- `StringUtils.mostFrequentChar()` - 最频繁字符
- `StringUtils.toCamelCase()` - 转驼峰命名
- `StringUtils.toSnakeCase()` - 转蛇形命名
- `StringUtils.isValidEmail()` - 邮箱格式验证
- `StringUtils.isValidUrl()` - URL格式验证

### 🔐 安全工具包 (Security)
加密、哈希和编码处理工具：

#### Base编码 (Security.BaseEncoding)
- `BaseEncoding.encodeBase64()` - Base64编码
- `BaseEncoding.decodeBase64()` - Base64解码
- `BaseEncoding.encodeBase64Url()` - URL安全Base64编码
- `BaseEncoding.encodeBase32()` - Base32编码
- `BaseEncoding.encodeBase64NoPadding()` - 无填充Base64
- `BaseEncoding.encodeBase64Mime()` - MIME Base64编码
- `BaseEncoding.isValidBase64()` - Base64格式验证
- `BaseEncoding.base64ToBase64Url()` - Base64格式转换

#### 加密算法 (Security.CryptoUtils)
- `CryptoUtils.encryptAES()` - AES加密
- `CryptoUtils.decryptAES()` - AES解密
- `CryptoUtils.encryptRSA()` - RSA加密
- `CryptoUtils.decryptRSA()` - RSA解密
- `CryptoUtils.generateRSAKeyPair()` - RSA密钥对生成
- `CryptoUtils.encryptCaesar()` - 凯撒密码
- `CryptoUtils.encryptVigenere()` - 维吉尼亚密码
- `CryptoUtils.encryptXOR()` - XOR加密
- `CryptoUtils.encryptROT13()` - ROT13加密
- `CryptoUtils.encryptAtbash()` - Atbash密码
- `CryptoUtils.checkPasswordStrength()` - 密码强度检查
- `CryptoUtils.generateRandomKey()` - 随机密钥生成

#### 哈希算法 (Security.HashUtils)
- `HashUtils.md5()` - MD5哈希
- `HashUtils.sha1()` - SHA-1哈希
- `HashUtils.sha256()` - SHA-256哈希
- `HashUtils.sha512()` - SHA-512哈希
- `HashUtils.sha3_256()` - SHA3-256哈希
- `HashUtils.sha3_512()` - SHA3-512哈希
- `HashUtils.hmacMD5()` - HMAC-MD5
- `HashUtils.hmacSHA256()` - HMAC-SHA256
- `HashUtils.hmacSHA512()` - HMAC-SHA512
- `HashUtils.pbkdf2()` - PBKDF2密钥派生
- `HashUtils.crc32()` - CRC32校验
- `HashUtils.adler32()` - Adler-32校验
- `HashUtils.hashFile()` - 文件哈希
- `HashUtils.constantTimeEquals()` - 常量时间比较
- `HashUtils.verifyHash()` - 哈希验证

#### 椭圆曲线密码 (Security.ECCUtils)
- `ECCUtils.generateKeyPair()` - 默认曲线生成密钥对 (secp256r1)
- `ECCUtils.generateKeyPair(String curve)` - 指定曲线生成密钥对
- `ECCUtils.generateKeyPair(int keySize)` - 指定密钥长度生成密钥对
- `ECCUtils.encrypt()` - ECIES加密
- `ECCUtils.decrypt()` - ECIES解密
- `ECCUtils.sign()` - ECDSA签名
- `ECCUtils.verify()` - ECDSA验签
- `ECCUtils.generateKeyStrings()` - 生成Base64编码的密钥对字符串
- `ECCUtils.getKeyPairFromStrings()` - 从字符串还原密钥对
- `ECCUtils.getSupportedCurves()` - 获取支持的曲线列表
- `ECCUtils.getKeySize()` - 获取指定曲线的密钥长度
- `ECCUtils.getCurveFromPrivateKey()` - 从私钥获取曲线名称
- `ECCUtils.getCurveFromPublicKey()` - 从公钥获取曲线名称

## 使用示例

### 基本使用
```java
import top.mc_plfd_host.Messages.Print;
import top.mc_plfd_host.Messages.Input;
import top.mc_plfd_host.Logger.Logger;
import top.mc_plfd_host.Logger.LoggerFactory;
import top.mc_plfd_host.Logger.LogLevel;
import top.mc_plfd_host.Configs.Json;
import top.mc_plfd_host.Configs.Yaml;
import top.mc_plfd_host.Configs.Properties;
import top.mc_plfd_host.Configs.Toml;
import top.mc_plfd_host.Configs.Cfg;
import top.mc_plfd_host.Configs.Xml;
import top.mc_plfd_host.Configs.Nbt;
import top.mc_plfd_host.Algorithms.Sort;
import top.mc_plfd_host.Algorithms.Search;
import top.mc_plfd_host.Algorithms.MathUtils;
import top.mc_plfd_host.Algorithms.StringUtils;
import top.mc_plfd_host.Security.BaseEncoding;
import top.mc_plfd_host.Security.CryptoUtils;
import top.mc_plfd_host.Security.HashUtils;
import top.mc_plfd_host.Security.ECCUtils;

public class Example {
    private static final Logger logger = LoggerFactory.getLogger(Example.class);
    
    public static void main(String[] args) {
        // 设置全局日志级别
        LoggerFactory.setGlobalLevel(LogLevel.DEBUG);
        
        // 日志功能
        logger.info("Application started successfully");
        logger.debug("Debug information");
        logger.warn("Warning message");
        logger.error("Error occurred", new RuntimeException("Test exception"));
        
        // 打印功能
        Print.info("Application started successfully");
        Print.error("An error occurred");
        
        // 输入功能
        String name = Input.string("Enter your name:");
        int age = Input.integer("Enter your age:");
        boolean isStudent = Input.bool("Are you a student?");
        String hobby = Input.select("Select hobby:", "Programming", "Music", "Sports");
        
        // JSON处理
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("age", age);
        boolean success = Json.writeToFile(data, "config.json");
        if (success) {
            logger.info("Configuration saved successfully");
        }
        
        // YAML处理
        Yaml.writeToFile(data, "config.yaml");
        
        // Properties处理
        Map<String, String> props = new HashMap<>();
        props.put("app.name", "MyApp");
        props.put("app.version", "1.0.0");
        Properties.writeToFile(props, "config.properties");
        
        // TOML处理
        Toml.writeToFile(data, "config.toml");
        
        // CFG处理
        Map<String, String> cfg = new HashMap<>();
        cfg.put("host", "localhost");
        cfg.put("port", "8080");
        Cfg.writeToFile(cfg, "config.cfg");
        
        // XML处理
        Xml.writeToFile(data, "config.xml");
        
        // NBT处理（Minecraft）
        Map<String, Object> nbtData = new HashMap<>();
        nbtData.put("PlayerName", "Steve");
        nbtData.put("Health", 20);
        nbtData.put("Position", new Object[]{1.0, 64.0, 1.0});
        nbtData.put("Inventory", Arrays.asList("diamond_sword", "apple"));
        Nbt.writeToFile(nbtData, "player.dat", true); // 压缩格式
        
        // 算法演示
        Integer[] numbers = {5, 2, 8, 1, 9, 3};
        
        // 排序算法
        Sort.quickSort(numbers);
        logger.info("Sorted array: " + Arrays.toString(numbers));
        
        // 搜索算法
        int index = Search.binarySearch(numbers, 8);
        logger.info("Found 8 at index: " + index);
        
        // 数学算法
        long gcd = MathUtils.gcd(48, 18);
        logger.info("GCD of 48 and 18: " + gcd);
        
        boolean isPrime = MathUtils.isPrime(97);
        logger.info("Is 97 prime? " + isPrime);
        
        long fib = MathUtils.fibonacci(10);
        logger.info("Fibonacci(10): " + fib);
        
        // 字符串算法
        String text = "Hello World";
        String reversed = StringUtils.reverse(text);
        logger.info("Reversed: " + reversed);
        
        boolean isPalindrome = StringUtils.isPalindrome("racecar");
        logger.info("Is 'racecar' palindrome? " + isPalindrome);
        
        String camelCase = StringUtils.toCamelCase("hello world example");
        logger.info("Camel case: " + camelCase);
        
        // 安全工具演示
        // Base编码
        String original = "Hello World";
        String base64Encoded = BaseEncoding.encodeBase64(original);
        String base64Decoded = BaseEncoding.decodeBase64(base64Encoded);
        logger.info("Base64: " + base64Encoded + " -> " + base64Decoded);
        
        // 哈希算法
        String md5Hash = HashUtils.md5(original);
        String sha256Hash = HashUtils.sha256(original);
        logger.info("MD5: " + md5Hash);
        logger.info("SHA-256: " + sha256Hash);
        
        // 简单加密
        String caesarEncrypted = CryptoUtils.encryptCaesar(original, 3);
        String caesarDecrypted = CryptoUtils.decryptCaesar(caesarEncrypted, 3);
        logger.info("Caesar: " + original + " -> " + caesarEncrypted + " -> " + caesarDecrypted);
        
        // XOR加密
        String xorKey = "secret";
        String xorEncrypted = CryptoUtils.encryptXOR(original, xorKey);
        String xorDecrypted = CryptoUtils.decryptXOR(xorEncrypted, xorKey);
        logger.info("XOR: " + original + " -> " + xorEncrypted + " -> " + xorDecrypted);
        
        // 密码强度检查
        CryptoUtils.PasswordStrength strength = CryptoUtils.checkPasswordStrength("MyPassword123!");
        logger.info("Password strength: " + strength.getMessage());
        
        // ECC椭圆曲线密码演示
        ECCUtils.ECCKeyPair eccKeyPair = ECCUtils.generateKeyPair();
        String eccMessage = "Secret Message";
        String eccEncrypted = ECCUtils.encrypt(eccMessage, eccKeyPair);
        String eccDecrypted = ECCUtils.decrypt(eccEncrypted, eccKeyPair);
        logger.info("ECC ECIES: " + eccMessage + " -> " + eccEncrypted + " -> " + eccDecrypted);
        
        String dataToSign = "Data to sign";
        String signature = ECCUtils.sign(dataToSign, eccKeyPair);
        boolean isValid = ECCUtils.verify(dataToSign, signature, eccKeyPair);
        logger.info("ECDSA signature valid: " + isValid);
        
        // 关闭输入
        Input.close();
        
        logger.info("Application finished");
    }
}
```

### 安全工具包示例
```java
// Base编码示例
String text = "Hello World";

// Base64编码
String base64 = BaseEncoding.encodeBase64(text);
String base64Url = BaseEncoding.encodeBase64Url(text);
String base64NoPadding = BaseEncoding.encodeBase64NoPadding(text);

// Base64解码
String decoded = BaseEncoding.decodeBase64(base64);
String decodedUrl = BaseEncoding.decodeBase64Url(base64Url);

// 格式验证
boolean isValid = BaseEncoding.isValidBase64(base64);

// 加密算法示例
// AES加密
String aesEncrypted = CryptoUtils.encryptAES("Secret Message", "myPassword123");
String aesDecrypted = CryptoUtils.decryptAES(aesEncrypted, "myPassword123");

// RSA加密
KeyPair rsaKeyPair = CryptoUtils.generateRSAKeyPair(2048);
String publicKeyString = CryptoUtils.getPublicKeyString(rsaKeyPair.getPublic());
String privateKeyString = CryptoUtils.getPrivateKeyString(rsaKeyPair.getPrivate());

String rsaEncrypted = CryptoUtils.encryptRSA("RSA Message", rsaKeyPair.getPublic());
String rsaDecrypted = CryptoUtils.decryptRSA(rsaEncrypted, rsaKeyPair.getPrivate());

// 经典密码
String caesar = CryptoUtils.encryptCaesar("Hello World", 3);
String vigenere = CryptoUtils.encryptVigenere("Hello World", "KEY");
String xor = CryptoUtils.encryptXOR("Hello World", "secret");
String rot13 = CryptoUtils.encryptROT13("Hello World");
String atbash = CryptoUtils.encryptAtbash("Hello World");

// 哈希算法示例
String md5 = HashUtils.md5("Hello World");
String sha1 = HashUtils.sha1("Hello World");
String sha256 = HashUtils.sha256("Hello World");
String sha512 = HashUtils.sha512("Hello World");

// HMAC
String hmacMd5 = HashUtils.hmacMD5("Hello World", "secret");
String hmacSha256 = HashUtils.hmacSHA256("Hello World", "secret");

// 密钥派生
String pbkdf2Key = CryptoUtils.deriveKey("password", "salt", 10000, 256);

// 校验和
int crc32 = HashUtils.crc32("Hello World");
int adler32 = HashUtils.adler32("Hello World");

// 文件哈希
String fileMd5 = HashUtils.md5File("example.txt");

// 哈希验证
boolean isValid = HashUtils.verifyHash("Hello World", md5, "MD5");

// 密码强度检查
CryptoUtils.PasswordStrength strength1 = CryptoUtils.checkPasswordStrength("weak");
CryptoUtils.PasswordStrength strength2 = CryptoUtils.checkPasswordStrength("StrongP@ssw0rd123!");
CryptoUtils.PasswordStrength strength3 = CryptoUtils.checkPasswordStrength("VeryStr0ng!@#2024");

// 随机密钥生成
String randomKey = CryptoUtils.generateRandomKey(32);

// ECC椭圆曲线密码示例
// 生成ECC密钥对（默认secp256r1曲线）
ECCUtils.ECCKeyPair ecKeyPair = ECCUtils.generateKeyPair();

// 使用指定曲线生成密钥对
ECCUtils.ECCKeyPair ecKeyPair384 = ECCUtils.generateKeyPair("secp384r1");

// 生成Base64编码的密钥字符串
String[] keyStrings = ECCUtils.generateKeyStrings();
String publicKeyStr = keyStrings[0];
String privateKeyStr = keyStrings[1];

// 从字符串还原密钥对
ECCUtils.ECCKeyPair restoredKeyPair = ECCUtils.getKeyPairFromStrings(publicKeyStr, privateKeyStr);

// 获取支持的曲线列表
String[] curves = ECCUtils.getSupportedCurves();
logger.info("Supported curves: " + String.join(", ", curves));

// ECIES加密/解密
String eccMessage = "Secret ECC Message";
String eccEncrypted = ECCUtils.encrypt(eccMessage, ecKeyPair);
String eccDecrypted = ECCUtils.decrypt(eccEncrypted, ecKeyPair);
logger.info("ECC ECIES: " + eccMessage + " -> " + eccEncrypted + " -> " + eccDecrypted);

// ECDSA签名/验签
String dataToSign = "Data to sign";
String signature = ECCUtils.sign(dataToSign, ecKeyPair);
boolean isValid = ECCUtils.verify(dataToSign, signature, ecKeyPair);
logger.info("ECDSA signature valid: " + isValid);

// 获取密钥信息
String curve = ecKeyPair.getCurve();
int keySize = ecKeyPair.getKeySize();
logger.info("Curve: " + curve + ", Key size: " + keySize);
```

### 算法工具包示例
```java
// 排序算法示例
Integer[] array = {64, 34, 25, 12, 22, 11, 90};

// 不同排序算法
Sort.bubbleSort(array.clone());
Sort.quickSort(array.clone());
Sort.mergeSort(array.clone());

// 性能比较
Sort.SorttingStats stats = Sort.getSortingStats(array, "quick");
logger.info(stats.toString());

// 搜索算法示例
String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};
int index = Search.binarySearch(names, "Charlie");
List<Integer> positions = Search.findAllOccurrences(names, "Alice");

// 特殊搜索
int[] rotated = {4, 5, 6, 7, 0, 1, 2, 3};
int found = Search.searchInRotated(rotated, 5);
int peak = Search.findPeak(new int[]{1, 3, 20, 4, 1});

// 数学算法示例
long gcd = MathUtils.gcd(48, 18, 30);
long lcm = MathUtils.lcm(4, 6, 8);
long power = MathUtils.power(2, 10);
long modPower = MathUtils.modPower(7, 256, 13);

// 质数相关
boolean[] primes = MathUtils.sieveOfEratosthenes(100);
List<Integer> primeList = MathUtils.getPrimes(50);
List<Long> factors = MathUtils.primeFactorization(84);

// 数列计算
long fib = MathUtils.fibonacci(20);
long[] fibSeq = MathUtils.fibonacciSequence(10);
long fact = MathUtils.factorial(6);
long comb = MathUtils.combination(10, 3);
long perm = MathUtils.permutation(10, 3);

// 统计计算
int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
double avg = MathUtils.average(data);
double median = MathUtils.median(data);
double stdDev = MathUtils.standardDeviation(data);

// 字符串算法示例
String text = "Hello World";
String reversed = StringUtils.reverse(text);
boolean isPal = StringUtils.isPalindrome("racecar");
boolean isAnagram = StringUtils.areAnagrams("listen", "silent");

// 字符串处理
String unique = StringUtils.removeDuplicates("programming");
String lcp = StringUtils.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
List<String> perms = StringUtils.permutations("abc");

// 格式转换
String camel = StringUtils.toCamelCase("hello_world_example");
String snake = StringUtils.toSnakeCase("HelloWorldExample");
String pascal = StringUtils.toPascalCase("hello world example");

// 验证和距离
boolean isEmail = StringUtils.isValidEmail("user@example.com");
boolean isUrl = StringUtils.isValidUrl("https://www.example.com");
int editDist = StringUtils.levenshteinDistance("kitten", "sitting");

// 字符串分析
char firstUnique = StringUtils.firstNonRepeatingChar("swiss");
char mostFreq = StringUtils.mostFrequentChar("mississippi");
boolean allUnique = StringUtils.hasAllUniqueChars("abcdef");
```

### NBT文件处理示例
```java
// 创建Minecraft玩家数据
Map<String, Object> playerData = new HashMap<>();
playerData.put("PlayerName", "Alex");
playerData.put("Health", 20);
playerData.put("FoodLevel", 20);
playerData.put("Experience", 150);
playerData.put("GameType", 0);

// 位置信息
Map<String, Object> position = new HashMap<>();
position.put("X", 100.5);
position.put("Y", 64.0);
position.put("Z", -200.3);
playerData.put("Pos", position);

// 背包物品
List<Object> inventory = new ArrayList<>();
Map<String, Object> item1 = new HashMap<>();
item1.put("id", "minecraft:diamond_sword");
item1.put("Count", (byte) 1);
item1.put("Damage", (short) 0);
inventory.add(item1);

Map<String, Object> item2 = new HashMap<>();
item2.put("id", "minecraft:bread");
item2.put("Count", (byte) 32);
item2.put("Damage", (short) 0);
inventory.add(item2);

playerData.put("Inventory", inventory);

// 写入NBT文件（压缩格式）
boolean success = Nbt.writeToFile(playerData, "playerdata/Alex.dat", true);

// 读取NBT文件
Map<String, Object> loadedData = Nbt.readFromFile("playerdata/Alex.dat", true, "Player");
if (loadedData != null) {
    String playerName = (String) loadedData.get("PlayerName");
    int health = (Integer) loadedData.get("Health");
    Map<String, Object> pos = (Map<String, Object>) loadedData.get("Pos");
    double x = (Double) pos.get("X");
    double y = (Double) pos.get("Y");
    double z = (Double) pos.get("Z");
    
    logger.info("Player: " + playerName + ", Health: " + health);
    logger.info("Position: X=" + x + ", Y=" + y + ", Z=" + z);
}

// 处理世界数据
Map<String, Object> worldData = new HashMap<>();
worldData.put("LevelName", "MyWorld");
worldData.put("SpawnX", 0);
worldData.put("SpawnY", 64);
worldData.put("SpawnZ", 0);
worldData.put("GameType", 0);
worldData.put("Difficulty", 1);
worldData.put("Hardcore", false);

// 写入level.dat
Nbt.writeToFile(worldData, "level.dat", true);
```

### 日志系统示例
```java
// 获取Logger实例
Logger logger = LoggerFactory.getLogger(MyClass.class);
// 或者
Logger logger = LoggerFactory.getLogger("MyLogger");
// 或者自动获取
Logger logger = LoggerFactory.getLogger();

// 设置日志级别
logger.setLevel(LogLevel.DEBUG);
LoggerFactory.setGlobalLevel(LogLevel.INFO);

// 不同级别的日志输出
logger.trace("Detailed trace information");
logger.debug("Debug information for development");
logger.info("General application information");
logger.warn("Warning that should be noted");
logger.error("Error that occurred", new Exception("Detailed error"));

// 带异常的日志
try {
    // some code
} catch (Exception e) {
    logger.error("Operation failed", e);
}

// 条件日志输出
if (logger.isDebugEnabled()) {
    logger.debug("Expensive debug operation: " + computeExpensiveValue());
}
```

### 输入功能示例
```java
// 各种数据类型输入
String username = Input.string("用户名:");
int score = Input.integer("分数:");
double price = Input.decimal("价格:");
boolean enabled = Input.bool("是否启用:");

// 选择输入
String level = Input.select("选择难度:", "简单", "中等", "困难");

// 确认输入（带默认值）
String city = Input.confirm("所在城市", "北京");

// 多行输入
String[] content = Input.multiLine("输入内容:", "END");

// 列表输入
List<String> tags = Input.stringList("输入标签(逗号分隔):");
```

### 配置格式转换示例
```java
// JSON转其他格式
Yaml.convertJsonToYaml("config.json", "config.yaml");
Toml.convertJsonToToml("config.json", "config.toml");
Xml.convertJsonToXml("config.json", "config.xml");

// YAML转其他格式
Yaml.convertYamlToJson("config.yaml", "config.json");
Toml.convertYamlToToml("config.yaml", "config.toml");
Xml.convertYamlToXml("config.yaml", "config.xml");

// TOML转其他格式
Toml.convertTomlToJson("config.toml", "config.json");
Toml.convertTomlToYaml("config.toml", "config.yaml");
Xml.convertTomlToXml("config.toml", "config.xml");

// XML转其他格式
Xml.convertXmlToJson("config.xml", "config.json");
Xml.convertXmlToYaml("config.xml", "config.yaml");
Xml.convertXmlToToml("config.xml", "config.toml");

// Properties操作
Properties.setProperty("config.properties", "debug", "true");
String version = Properties.getProperty("config.properties", "app.version", "1.0.0");

// CFG操作
Cfg.setValue("config.cfg", "timeout", "30");
String host = Cfg.getValue("config.cfg", "host", "localhost");
Cfg.addComment("config.cfg", "Database configuration");
```

### 对象序列化
```java
// 定义配置类
public class AppConfig {
    private String appName;
    private int port;
    private boolean debug;
    // getters and setters...
}

// 使用
AppConfig config = new AppConfig();
config.setAppName("MyApp");
config.setPort(8080);
config.setDebug(true);

// 保存为JSON
Json.writeToFile(config, "app-config.json");

// 从JSON读取
AppConfig loadedConfig = Json.readFromFile("app-config.json", AppConfig.class);
```

## 依赖

本项目使用以下库进行配置文件处理：
- `jackson-databind` 2.15.2 - 核心数据绑定
- `jackson-dataformat-yaml` 2.15.2 - YAML格式支持
- `jackson-dataformat-toml` 2.15.2 - TOML格式支持
- `jackson-dataformat-xml` 2.15.2 - XML格式支持
- `jdom2` 2.0.6 - XML处理辅助库
- `bouncycastle:bcprov-jdk15on` 1.70 - 加密和哈希库

## 运行

```bash
# 编译项目
./gradlew build
```

## 项目结构

```
src/main/java/top/mc_plfd_host/
├── Messages/
│   ├── Print.java          # 打印功能
│   └── Input.java          # 输入功能
├── Logger/
│   ├── Logger.java         # 日志接口
│   ├── LogLevel.java       # 日志级别枚举
│   ├── SimpleLogger.java    # 简单日志实现
│   └── LoggerFactory.java   # 日志工厂
├── Types/
│   └── NbtTagType.java     # NBT标签类型枚举
├── Algorithms/
│   ├── Sort.java           # 排序算法
│   ├── Search.java         # 搜索算法
│   ├── MathUtils.java      # 数学算法
│   └── StringUtils.java    # 字符串算法
├── Security/
│   ├── BaseEncoding.java    # Base编码工具
│   ├── CryptoUtils.java     # 加密工具
│   ├── HashUtils.java      # 哈希工具
│   └── ECCUtils.java       # 椭圆曲线密码工具
├── Configs/
│   ├── Json.java           # JSON处理
│   ├── Yaml.java           # YAML处理
│   ├── Properties.java     # Properties处理
│   ├── Toml.java           # TOML处理
│   ├── Cfg.java            # CFG处理
│   ├── Xml.java            # XML处理
│   └── Nbt.java            # NBT处理
└── RainbowUtils.java       # 主入口和文档
```

## 许可证

MIT License
