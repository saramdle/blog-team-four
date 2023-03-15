export default function Container({ children }: { children: React.ReactNode }) {
  return <div className='mx-auto max-w-6xl'>{children}</div>;
}
