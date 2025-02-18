import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ErrorBoundary } from 'components/ErrorBoundary/ErrorBoundary';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import Layout from 'pages/Layout/Layout';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      useErrorBoundary: true,
      // suspense: true, 
    },
  },
});

const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <ErrorBoundary>
        <Layout />
      </ErrorBoundary>
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  );
};

export default App;
