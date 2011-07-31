### ![blitz.io](http://blitz.io/images/logo2.png)

### Make load and performance a fun sport.

* Run a sprint from around the world
* Rush your API and website to scale it out
* Condition your site around the clock

## Getting started

Login to [blitz.io](http://blitz.io) and in the blitz bar type:
    
    --api-key

On your **pom.xml**

    <dependency>
        <groupId>io.blitz</groupId>
        <artifactId>api-client</artifactId>
    </dependency>

Then

**Sprint**

```javascript
Sprint s = new Sprint("your@account.com", "aqbcdge-sjfkgurti-sjdhgft-skdiues");
s.setUrl(new URL("http://your.cool.app"));
s.addListener(new ISprintListener() {

    public void onError(ErrorResult result) {
        System.err.println("ERROR!");
    }

    public void onSuccess(SprintResult result) {
        System.err.println("SUCCESS!");
    }
});
s.execute();
```

**Rush**

```javascript
Rush r = new Rush("your@account.com", "aqbcdge-sjfkgurti-sjdhgft-skdiues");
r.setUrl(new URL("http://your.cool.app"));
Collection<Interval> intervals = new ArrayList<Interval>();
intervals.add(new Interval(1, 10, 10));
r.setPattern(new Pattern(intervals));
r.addListener(new IRushListener() {

    public void onError(ErrorResult result) {
        System.err.println("ERROR!");
    }

    public void onSuccess(RushResult result) {
        System.err.println("SUCCESS!");
    }
});
r.execute();
```

## Dependencies

If you are not using maven, you must download the dependencies jar archives and 
add them to your classpath.

### Runtime

Needed to use the API client.

* Google Gson v1.7.1 [Homepage](http://code.google.com/p/google-gson/)
* Apache Commons Codec v1.5 [Homepage](http://commons.apache.org/codec/)

### Testing

Needed to run the unit tests.

* JUnit v4.8.2 [Homepage](http://www.junit.org/)
* Mockito v1.8.5 [Homepage](http://mockito.org/)