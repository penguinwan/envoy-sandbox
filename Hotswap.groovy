def liveMethod(def input) {
    println "headers ${input.in.headers}"
    input.in.headers['CamelHttpResponseCode'] = '200'
    input.in.body = '''{"message":"ok"}'''
}