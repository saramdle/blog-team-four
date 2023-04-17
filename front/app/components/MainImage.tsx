import Image, { StaticImageData } from "next/image";

type Props = {
  title: string;
  src: StaticImageData | string;
  createdAt?: string;
  author?: string;
};

export default function MainImage({ title, src, author, createdAt }: Props) {
  return (
    <div className='main-img-ratio relative mb-4 overflow-hidden'>
      <div className='absolute z-10 flex h-full w-full flex-col items-center justify-center gap-3 p-10 font-bold text-white'>
        <h1 className='my-auto text-4xl'>{title}</h1>
        <h2 className='text-2xl'>{author}</h2>
        <p>{createdAt?.slice(0, 10)}</p>
      </div>
      <Image
        alt='home-image'
        src={src}
        fill
        sizes='50vw'
        className='object-cover'
        priority
      />
      <div className='absolute inset-0 bg-black/40' />
    </div>
  );
}
