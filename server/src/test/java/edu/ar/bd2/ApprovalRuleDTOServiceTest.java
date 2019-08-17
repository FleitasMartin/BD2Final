package edu.ar.bd2;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ar.bd2.ApprovalRuleDTOServiceTest.SpringTestConfig;
import edu.ar.bd2.api.dto.ApprovalRuleDTO;
import edu.ar.bd2.api.exception.NotFoundException;
import edu.ar.bd2.helpers.ApprovalRuleDTOHelper;
import edu.ar.bd2.service.ApprovalRuleDTOService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringTestConfig.class)
public class ApprovalRuleDTOServiceTest {

	@Configuration
	@ComponentScan(basePackageClasses = ApprovalRuleDTOServiceTest.class)
	public static class SpringTestConfig {
	}

	@Autowired
	private ApprovalRuleDTOService service;

	private ApprovalRuleDTO request;

	private ApprovalRuleDTO versioned;

	@Before
	public void beforeTest() {
		this.request = ApprovalRuleDTOHelper.defaultApprovalRuleDTO();
	}

	@Test
	public void concurrentUpdateTest() {
		this.versioned = this.service.create(this.request);
		assertNotNull(this.service.update(this.versioned));
		assertThatThrownBy(() -> this.service.update(this.versioned))
				.isInstanceOf(OptimisticLockingFailureException.class);
	}

	@Test
	public void createApprovalRuleTest() {
		this.versioned = this.service.create(this.request);
		assertTrue(versioned.getVersion() == 1);
		assertNotNull(versioned.getId());
	}

	@Test
	public void getApprovalRuleTest() {
		this.versioned = this.service.create(this.request);
		ApprovalRuleDTO retrieved = this.service.getById(this.versioned.getId());
		assertEquals(this.versioned.getId(), retrieved.getId());
		assertEquals(this.versioned.getVersion(), retrieved.getVersion());
	}

	@Test
	public void deleteApprovalRuleTest() {
		ApprovalRuleDTO versioned = this.service.create(this.request);
		this.service.delete(versioned.getId());
		assertThatThrownBy(() -> this.service.getById(versioned.getId())).isInstanceOf(NotFoundException.class);
	}

	@After
	public void afterTest() {
		if (Objects.nonNull(this.versioned) && Objects.nonNull(this.versioned.getId())) {
			this.service.delete(this.versioned.getId());
		}
	}

}
