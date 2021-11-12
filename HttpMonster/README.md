模板模式做参数收集模块

责任链模式做网络请求模块

像PostMan一样使用Http

new xxxRequest().register(new HttpCallback(){
    ...
}).send();

HttpMonster.getIntent().register(xxxRequest.class , new HttpCallback(){
    ...
});



TODO
- Params能不能优化一下?






