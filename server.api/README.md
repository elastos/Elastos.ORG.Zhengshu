# BLESSING STAR SERVICE API
==============

## Summary

This repo provide simple HTTP Restful API for  developers to send user blessing on elastos did side chain.

## Build with maven

In project directory, use maven command:
```Shell
$uname mvn clean compile package
```
If there is build success, Then the package blessing.star.service.api-0.0.1.jar will be in target directory.

## Configure project properties

in file: application.properties.

```yaml
## Block agent service url
blockagent.prefix      = https://api-wallet-did-testnet.elastos.org

## API Access Key
accesskey.id  = org.elastos.bless.star
accesskey.secret = SmATCfWN1LqHH8b5bbRDbaz0IMhA5u

## DID
did.node =  http://localhost:21334
did.did = iV8D3SfqUZUomodfmarPHdnfCScnNMgipJ
did.privateKey = 9DCD16F1E6975E056E569AAED5D0D149FE95DDB603A3DD85D61F08D145C8B770
did.publicKey = 03513E5A68ED091227CC507EA16171EA53EF584AB30968DFAB40C44C2910D7EE95

```

## Run

Copy blessing.star.service.api-0.0.1.jar to your deploy directory.
then use jar command to run this spring boot application.

```shell
$uname java -jar blessing.star.service.api-0.0.1.jar
```
## Web Service APIs

### Send Blessing on chain
```yaml
HTTP: POST
URL: /api/1/star/blessing
HEADERS: 
    Content-Type: application/json
    X-Elastos-Agent-Auth: {"auth":"auth_code","id":"org.elastos.bless.star","time":"system time(long)"}
```

For example:
We Create HTTP header "X-Elastos-Agent-Auth" lile:
```java
import org.apache.shiro.crypto.hash.SimpleHash;
String createAuthHeaderValue(){
    String acc_id = "org.elastos.bless.star";
    String acc_secret = "SmATCfWN1LqHH8b5bbRCEaz0IMhA5u";
    long time = new Date().getTime();
    String strTime = String.valueOf(time);
    SimpleHash hash = new SimpleHash("md5", secret, strTime);
    String auth = hash.toHex();
    Map<String, String> map = new HashMap<>();
    map.put("id", id);
    map.put("time", String.valueOf(time));
    map.put("auth", auth);
    String X-Elastos-Agent-Auth_value = JSON.toJSONString(map);
    return X-Elastos-Agent-Auth_value;
}
```
Then We send blessing up chain like this:
```url
http://localhost:8094/api/1/star/blessing
```
Post body like
```json
{
	"star_name":"TestStar1",
	"user_name":"TestUser",
	"user_id":"TestId",
	"blessing":"Bless for test"
}
```

If Success, we will get response like:
```json
{
    "data": {
        "txid": "efd47380fa1dd8cc7823ae7cbc513bf7e69ed05aaef21c071a582607a6d4d246"
    },
    "status": 200
}
```

