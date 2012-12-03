package test.response;

import jade.core.Agent;
import test.common.JadeController;
import test.common.TestException;
import test.common.TestGroup;
import test.common.TestUtility;
import test.common.TesterAgent;

public class ResponseTesterAgent extends TesterAgent {
	private static final long serialVersionUID = 1L;

	@Override
	protected TestGroup getTestGroup() {
		TestGroup tg = new TestGroup("test/response/responseTesterAgent.xml") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void initialize(Agent a) throws TestException {
				try {
				}
				catch (Exception e) {
					throw new TestException("Error initializing TestGroup", e);
				}
			}
			
			@Override
			public void shutdown(Agent a) {
				
			}
			
			};
		return tg;
	}

}
