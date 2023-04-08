import Image, { StaticImageData } from "next/image";

type Props = {
  title: string;
  src: StaticImageData | string;
};

export default function MainImage({ title, src }: Props) {
  return (
    <div className='main-img-ratio relative overflow-hidden'>
      <h1 className='absolute z-10 flex h-full w-full items-center justify-center font-bold text-white sm:text-3xl'>
        {title}
      </h1>
      <Image
        alt='home-image'
        src={src}
        fill
        sizes='50vw'
        className='object-cover'
      />
      <div className='absolute inset-0 bg-black/40' />
    </div>
  );
}
