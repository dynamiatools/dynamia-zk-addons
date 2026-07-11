# CLAUDE.md — Dynamia ZK Addons

These guidelines are for contributing to the **Dynamia ZK Addons** library itself.

## Project Overview

Dynamia ZK Addons is a comprehensive library of extensions, utilities, and components that enhance the [ZK Framework](https://www.zkoss.org). It complements the [Dynamia Tools framework](https://github.com/dynamiatools/framework) but is published as an independent Maven artifact (`tools.dynamia:dynamia-zk-addons`), requires **Java 21**, and consumers must add ZK as a dependency separately.

## Claude Code / IntelliJ MCP Integration

- Prefer the `idea` MCP server tools over generic file/shell exploration when working inside this repo:
  - **Finding references** — use `mcp__idea__search_symbol` / symbol-aware search instead of grep/ripgrep.
  - **Reading symbol info** — use `mcp__idea__get_symbol_info` for accurate type/member info instead of guessing from text.
  - **Renaming symbols** — use `mcp__idea__rename_refactoring` for safe, project-wide renames instead of manual find/replace.
  - **Structural search** — use `mcp__idea__search_structural` / `mcp__idea__get_structural_patterns` for pattern-based code search (e.g. finding all ZK component subclasses).
  - **Diagnostics/problems** — use `mcp__idea__get_file_problems` / `mcp__idea__get_inspections` instead of manually re-reading files for errors.
  - **Navigating files** — use `mcp__idea__find_files_by_name_keyword`, `mcp__idea__find_files_by_glob`, `mcp__idea__list_directory_tree` before falling back to `find`/`ls`.
  - **Editing** — use `mcp__idea__replace_text_in_file` / `mcp__idea__apply_patch` so changes go through the IDE and stay in sync with its indices.
- **Never excavate `.jar` files** (unzipping, extracting classes, browsing decompiled sources from `~/.m2` caches, e.g. to inspect the ZK Framework or DynamiaTools APIs) to inspect a dependency's API. IntelliJ already indexes all project dependencies — use `mcp__idea__search_symbol`, `mcp__idea__get_symbol_info`, or `mcp__idea__find_files_by_name_keyword` to resolve classes/methods from JARs directly through the IDE index. Only fall back to raw shell/grep exploration if the MCP server is unavailable or the target is genuinely outside the IDE's index.
- The IDE's semantic understanding is far more accurate than text-based search — treat grep/ripgrep/manual file scanning as a last resort for symbol-level work in this repo.

## Project Structure

```
dynamia-zk-addons/
├── src/
│   ├── main/
│   │   ├── java/tools/dynamia/...   # Component and utility classes
│   │   └── resources/
│   │       ├── metainfo/zk/          # ZK component metainfo (lang addons, XML config)
│   │       └── web/                  # Static web assets (JS, CSS) for components
│   └── test/
│       └── java/tools/dynamia/...    # Unit tests
├── pom.xml
└── README.md
```

## Coding Guidelines

- Code must be **clean, modular, and reusable**, following existing package conventions under `tools.dynamia`.
- Each ZK component should have its Java class registered in the corresponding `metainfo/zk` lang-addon XML.
- Keep client-side assets (JS/CSS) under `src/main/resources/web/dynamia-zk-addons` consistent with the component they support.
- Follow **Java 21** best practices; do not use APIs beyond what the declared Java version supports.
- Don't add features, refactor, or introduce abstractions beyond what the task requires. Match the existing component's style.
- New components should follow the pattern of existing ones (e.g. `Card`, `Infobox`, `Badge`, `Alert`, `Colorbox`, `EnumLabel`, `Booleanbox`, `SignaturePad`) for consistency in API design.

## Documentation Guidelines (Javadoc)

- Every class and public method must have **Javadoc in English**.
- Keep comments **descriptive**: explain what the component/method does and why it exists.
- Do **not** alter original code logic when adding documentation.
- Include `@param`, `@return`, and `@throws` where applicable.
- Add usage examples with `<pre>{@code ... }</pre>` when helpful.

## Extra Notes

- This library depends on the ZK Framework being present in the consuming project; do not assume Spring Boot or Dynamia Tools framework classes are available unless already used elsewhere in the codebase.
- Keep new components self-contained and demo-able via the [demo-my-books](https://github.com/dynamiatools/demo-my-books) reference project style.
- Versioning follows standard Maven Central releases (see `pom.xml`), not the CalVer scheme used by the main framework.
