# ==========================================
# ENTERPRISE AUTOMATION MOTOR (MAKEFILE)
# ==========================================

MVN = mvn
PORT = 8085

.PHONY: all build test run clean port-status reset-network

all: clean build test

build:
	@echo "🛠️ Bundling Java target binaries and configurations..."
	$(MVN) clean package -DskipTests

test:
	@echo "🧪 Firing automated JUnit testing suites..."
	$(MVN) test

run:
	@echo "🚀 Launching active engine on http://localhost:$(PORT)"
	$(MVN) spring-boot:run

clean:
	@echo "🧹 Wiping temporary target build directories..."
	$(MVN) clean

port-status:
	@echo "🔍 Checking socket listeners on port $(PORT)..."
	@netstat -ano | findstr :$(PORT) || echo "✅ Target port is available."

reset-network:
	@echo "⚠️ Warning: Please close unneeded JVM tasks in your Task Manager if port $(PORT) stays blocked."
