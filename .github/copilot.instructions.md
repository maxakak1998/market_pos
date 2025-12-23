---
applyTo: '**'
---

# 🧠 Instructions for Copilot Agency

## 🏗️ Architecture & Structure

**Always think architecturally** and consider the overall codebase structure by referring to `flutter_tools/instructions/structure.instructions.md`.

Before creating anything (features, widgets, routes, APIs, etc.), check for existing pre-defined commands in `flutter_tools/instructions/base_command_line.instructions.md`.

## 📋 Feature-Specific Guidelines

### State Management & UI
- **Cubit**: Follow `flutter_tools/instructions/cubit.instructions.md`
- **Loading Overlays**: Follow `flutter_tools/instructions/add_loading.instructions.md`
- **ListView**: Follow `flutter_tools/instructions/listview.instructions.md`
- **Styling**: Follow `flutter_tools/instructions/style.instructions.md`

### Components & Widgets
- **Creating UI Components**: Follow `flutter_tools/instructions/create_ui.instructions.md`
- **Asset Management**: Covered in `flutter_tools/instructions/create_ui.instructions.md`

### Navigation & Routing
- **Navigation Patterns**: Follow `flutter_tools/instructions/navigation.instructions.md`

### Data & Business Logic
- **API Integration**: Follow `flutter_tools/instructions/api.instructions.md`
- **UseCase Implementation**: Follow `flutter_tools/instructions/usecase.instructions.md`
- **Pagination**: Follow `flutter_tools/instructions/pagination.instructions.md`
- **Repository Pattern**: Follow `flutter_tools/instructions/offline_repository_pattern.instructions.md` for online/offline repositories


### Feature Creation
When creating a new feature, follow:
- `flutter_tools/instructions/structure.instructions.md` for feature structure
- `flutter_tools/instructions/injecting.instructions.md` for dependency injection

### Figma Integration
When generating code from Figma:
- Follow all applicable instructions above
- Maintain architectural thinking
- Refer to `flutter_tools/instructions/figma.instructions.md` for Figma-specific guidelines

## ⚠️ Critical Requirements

**Interactive Feedback (MANDATORY)**

You **MUST ALWAYS** call `MCP interactive_feedback` in the following scenarios:
- Before asking any question that requires user input or decision
- After completing any user request (final confirmation)
- Before running terminal commands (ask for confirmation first)
- When expecting Yes/No responses
- **After providing ANY educational/technical explanations** (REQUIRED - to check if user needs more help)
- After answering documentation questions (e.g., "can users do X?", "how does Y work?")
- After code explanations or architecture discussions

**When NOT to use interactive feedback:**
- NEVER skip it - there are NO exceptions
- Every user interaction should end with interactive feedback

**Key Rule:** 
- If you answer a question, explain something, or complete a task → **ALWAYS call interactive_feedback at the end**
- Think of it as a closing handshake after every response

**Displaying Feedback:**
- When receiving feedback from the interactive prompt, **always display the feedback and your response in the Copilot chat window**
- Don't just respond in the mini prompt - make the conversation visible in the main chat
- Acknowledge what the user said and explain your next action

**Handling Feedback Responses:**
- If feedback is empty (user says nothing), **continue immediately** with the next step - do not wait for task completion
- If user skips/declines a terminal command, acknowledge it and continue with next step
- If feedback contains instructions or questions, address them first

**Feedback Loop Process:**
1. Call `MCP interactive_feedback` when needed
2. Wait for response
3. **Display the feedback in chat and respond there**
4. **If feedback is empty → continue immediately to next step**
5. If feedback has content → address it in chat, then call feedback again
6. Repeat until feedback is empty, then continue
7. Call final interactive feedback only when entire task is complete

**Never skip the interactive feedback step** at completion or when genuine questions are required.
