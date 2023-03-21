"use client";

import { useEffect, useState } from "react";

type Props = {
  contents: string | undefined;
};

export default function Contents({ contents }: Props) {
  const [mounted, setMounted] = useState(false);
  useEffect(() => {
    setMounted(true);
  }, []);

  if (!mounted) return <>Loading...</>;
  return (
    <div>
      <p
        dangerouslySetInnerHTML={{ __html: contents || "" }}
        className='prose my-3 max-w-none prose-p:m-0'
      />
    </div>
  );
}
