plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "1.9.25"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot 기본 설정
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation") // 데이터 검증

	// Kotlin 관련 설정
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // JSON 파싱
	implementation("org.jetbrains.kotlin:kotlin-reflect")               // Kotlin 리플렉션
	implementation("org.jetbrains.kotlin:kotlin-stdlib")                // Kotlin 기본 라이브러리

	// 데이터베이스 설정
	runtimeOnly("com.h2database:h2")             // 인메모리 DB (개발 및 테스트용)

	// 로깅 설정
	implementation("org.springframework.boot:spring-boot-starter-logging")
	implementation("ch.qos.logback:logback-classic")

	// QueryDSL
	// implementation("com.querydsl:querydsl-jpa")
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")

	// kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

	// javax.persistence 의존성 추가 (QueryDSL을 위한)
	implementation("javax.persistence:javax.persistence-api:2.2")

	// 개발 환경
	developmentOnly("org.springframework.boot:spring-boot-devtools") // 핫 리로드

	// 테스트
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
