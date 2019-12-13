@Grab(group = 'org.apache.camel', module = 'camel-core', version = '2.20.2')
@Grab(group = 'org.apache.camel', module = 'camel-netty4', version = '2.20.2')
@Grab(group = 'org.apache.camel', module = 'camel-netty4-http', version = '2.20.2')
@Grab(group = 'org.apache.camel', module = 'camel-groovy', version = '2.20.2')
@Grab('org.slf4j:slf4j-simple:1.6.6')

import io.netty.handler.codec.http.multipart.*
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.netty4.http.NettyHttpMessage
import org.apache.camel.impl.DefaultCamelContext

def camelContext = new DefaultCamelContext()

camelContext.addRoutes(new RouteBuilder() {
    def void configure() {
        from("netty4-http:http://0.0.0.0:8081").process { message ->
            new GroovyShell().parse( new File('Hotswap.groovy') ).with {
              liveMethod(message)
            }
        }
    }
})
camelContext.start()

addShutdownHook { camelContext.stop() }
synchronized (this) {
    this.wait()
}
