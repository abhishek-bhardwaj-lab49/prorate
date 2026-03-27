# Contributing to the Project

## Getting Started

Thank you for considering contributing! Here's how to set up the project locally:

### Prerequisites
- [ ] Install [Node.js](https://nodejs.org) (v18+ recommended)
- [ ] Install [npm](https://npmjs.org) or [yarn](https://yarn.dev)

### Installation
```sh
cd your-project-directory
npm install
# or
yarn install
```

## Project Setup

Configure your environment:
```sh
cp .env.example .env
npm run generate
# or
yarn generate
```

## Running the Project

Start the development server:
```sh
npm run dev
# or
yarn dev
```

Access the application at [http://localhost:3000](http://localhost:3000)

## Testing

Run all tests:
```sh
npm test
# or
yarn test
```

Run specific test file:
```sh
npm test -- --test-file=src/your-file.spec.ts
# or
yarn test -- --test-file=src/your-file.spec.ts
```

Test coverage:
```sh
npm run coverage
# or
yarn coverage
```

## Pull Request Guidelines

### Branching Convention
- Feature branches: `feature/short-description`
- Bug fixes: `fix/issue-slug`
- Hotfixes: `hotfix/critical-issue`

### Commit Messages
Follow [Conventional Commits](https://conventionalcommits.org):
```
type(scope): short description

body

footer
```

### Submitting a PR
1. Commit your changes
2. Push to your branch
3. Create a PR against `main`
4. Add reviewers (if configured)
5. Wait for review and testing
6. Address feedback
7. Merge once approved

### Review Process
- [ ] Automated tests must pass
- [ ] Code coverage must be at least 80%
- [ ] At least one reviewer must approve
- [ ] Code climate quality score must be good

Thanks again for contributing!