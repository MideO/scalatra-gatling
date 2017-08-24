package com.example.app.libs

trait MockedTestSimulation
  extends TestSimulation {
  before {
    TestStubServer.start()
  }

  after {
    TestStubServer.stop()
  }
}


trait LocalTestSimulation
  extends TestSimulation
  with TestApplication {
  before {
    start()
  }

  after {
    stop()
  }
}


trait LocalMockedTestSimulation
  extends LocalTestSimulation
  with MockedTestSimulation